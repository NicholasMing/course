package cn.mallwe.course.service;

import cn.mallwe.course.entity.Course;
import cn.mallwe.course.query.CourseQuery;
import cn.mallwe.course.result.CourseInfo;

import java.util.List;

public interface ICourseService {

    /**
     * 添加课程
     */
    boolean addCourse(CourseInfo courseInfo);

    /**
     * 查询课程
     */
    List<Course> queryCourse(CourseQuery query);


    /**
     * 查询数量
     */
    int count(CourseQuery query);
}
