package cn.mallwe.course.spider;

import cn.mallwe.course.enums.GradeEnum;
import cn.mallwe.course.result.CourseInfo;
import cn.mallwe.course.result.CourseResult;
import cn.mallwe.course.result.PackageResult;
import cn.mallwe.course.result.SysPackage;
import cn.mallwe.course.util.JsonUtils;
import cn.mallwe.course.util.RestTemplateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 抓取企鹅辅导课程数据
 */
@Service
public class CourseSpider {

    private static String INDEX_PC_DISCOVER = "https://fudao.qq.com/cgi-proxy/course/index_pc_discover?client=4&platform=3&version=30&grade={1}";
    private static String GET_COURSE_PACKAGE_INFO_URL = "https://fudao.qq.com/cgi-proxy/course/get_course_package_info?client=4&platform=3&version=30&course_package_id={1}";

    private RestTemplate restTemplate = new RestTemplate(RestTemplateUtil.clientHttpRequestFactory());

    private ExecutorService threadPool = Executors.newFixedThreadPool(10, Executors.defaultThreadFactory());

    @Getter
    private ConcurrentLinkedQueue<CourseInfo> taskQueue = new ConcurrentLinkedQueue<>();


    public void doSpider() {
        //全部年级
        List<GradeEnum> enumList = GradeEnum.getEnumList();
        for (GradeEnum gradeEnum : enumList) {
            int grade = gradeEnum.getValue();
            threadPool.execute(() -> {
                int packageId = getPackageId(grade);
                List<CourseInfo> courseList = getCourseList(packageId);
                courseList.forEach(courseInfo -> {
                    courseInfo.setGrade(grade);
                    taskQueue.offer(courseInfo);
                });
            });
        }
    }

    /**
     * 获取课程
     *
     * @param packageId
     * @return
     */
    public List<CourseInfo> getCourseList(int packageId) {
        ResponseEntity<String> res = restTemplate.exchange(
                GET_COURSE_PACKAGE_INFO_URL,
                HttpMethod.GET,
                createHttpEntity(),
                String.class, packageId);

        CourseResult result = JsonUtils.jsonToBean(parseResult(res.getBody()), CourseResult.class);
        if (result != null) {
            List<CourseInfo> courses = result.getCourses();
            return courses;
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * 获取系统课
     *
     * @param grade 年级
     * @return
     */
    public int getPackageId(int grade) {
        ResponseEntity<String> res = restTemplate.exchange(
                INDEX_PC_DISCOVER,
                HttpMethod.GET,
                createHttpEntity(),
                String.class, grade);

        PackageResult result = JsonUtils.jsonToBean(parseResult(res.getBody()), PackageResult.class);
        if (result != null) {
            List<SysPackage> list = result.getSysPackage();
            if (list != null) {
                return list.get(0).getPackageId();
            }
        }
        return 0;
    }

    private HttpEntity createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Referer", "https://fudao.qq.com");
        HttpEntity httpEntity = new HttpEntity(headers);
        return httpEntity;
    }

    private String parseResult(String body) {
        Map<String, Object> map = new HashMap();
        try {
            map = new ObjectMapper().readValue(body, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return JsonUtils.objectToJson(map.get("result"));
    }
}
