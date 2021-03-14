package cn.mallwe.course.config;

import cn.mallwe.course.result.CourseInfo;
import cn.mallwe.course.service.ICourseService;
import cn.mallwe.course.spider.CourseSpider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedQueue;

@Component
@Slf4j
public class CourseQueueCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CourseSpider courseSpider;
    @Autowired
    private ICourseService courseService;

    @Override
    public void run(String... args) {
        ConcurrentLinkedQueue<CourseInfo> taskQueue = courseSpider.getTaskQueue();
        while (true) {
            if (!taskQueue.isEmpty()) {
                try {
                    courseService.addCourse(taskQueue.poll());
                } catch (Exception e) {
                    log.error("写入失败{}", e.getMessage());
                }
            }
        }
    }
}
