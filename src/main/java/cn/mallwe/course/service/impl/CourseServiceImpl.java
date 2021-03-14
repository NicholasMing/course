package cn.mallwe.course.service.impl;

import cn.mallwe.course.entity.Course;
import cn.mallwe.course.query.CourseQuery;
import cn.mallwe.course.repository.CourseRepository;
import cn.mallwe.course.result.CourseInfo;
import cn.mallwe.course.result.Teacher;
import cn.mallwe.course.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public boolean addCourse(CourseInfo courseInfo) {
        Optional<Course> courseOptional = courseRepository.getById(courseInfo.getCid());
        if (courseOptional.isPresent()) {
            return false;
        }
        Course course = new Course();
        course.setCid(courseInfo.getCid());
        course.setName(courseInfo.getName());
        course.setTimePlan(courseInfo.getTimePlan());
        course.setGrade(courseInfo.getGrade());
        course.setSubject(courseInfo.getSubject());
        course.setStudentTotal(courseInfo.getClassInfo().getStudentTotal());
        course.setRealTotalApply(courseInfo.getClassInfo().getRealTotalApply());
        course.setAfAmount(courseInfo.getAfAmount());
        List<Teacher> tutorList = courseInfo.getClassInfo().getTutorList();
        course.setTutorName(tutorList.get(0).getName());
        course.setTeacherName(courseInfo.getTeList().get(0).getName());
        return courseRepository.addCourse(course);
    }

    @Override
    public List<Course> queryCourse(CourseQuery query) {
        List<Course> courseList = courseRepository.query(query);
        return courseList;
    }

    @Override
    public int count(CourseQuery query) {
        int count = courseRepository.count(query);
        return count;
    }
}
