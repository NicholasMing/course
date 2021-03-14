package cn.mallwe.course.controller;

import cn.mallwe.course.entity.Course;
import cn.mallwe.course.enums.GradeEnum;
import cn.mallwe.course.enums.SubjectEnum;
import cn.mallwe.course.query.CourseQuery;
import cn.mallwe.course.result.RestResponse;
import cn.mallwe.course.service.ICourseService;
import cn.mallwe.course.spider.CourseSpider;
import cn.mallwe.course.vo.CourseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    private CourseSpider courseSpider;
    @Autowired
    private ICourseService courseService;

    /**
     * 抓取企鹅辅导课程数据
     */
    @RequestMapping("spider")
    public RestResponse spider() {
        courseSpider.doSpider();
        return RestResponse.buildResult();
    }

    /**
     * 查询课程数据
     */
    @GetMapping("query")
    public RestResponse query(CourseQuery query) {
        List<CourseVO> courseVOList = new ArrayList<>();
        List<Course> courses = courseService.queryCourse(query);
        courses.forEach(course -> {
            CourseVO courseVO = new CourseVO();
            BeanUtils.copyProperties(course, courseVO);
            courseVO.setGradeName(GradeEnum.stateOf(course.getGrade()).getDesc());
            courseVO.setSubjectName(SubjectEnum.stateOf(course.getSubject()).getDesc());
            courseVOList.add(courseVO);
        });

        int count = courseService.count(query);

        Map<String, Object> map = new HashMap<>();
        map.put("list", courseVOList);
        map.put("total", count);

        return RestResponse.buildResult(map);
    }
}
