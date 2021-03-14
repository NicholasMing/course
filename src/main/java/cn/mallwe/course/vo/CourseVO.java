package cn.mallwe.course.vo;

import cn.mallwe.course.entity.Course;
import lombok.Data;

@Data
public class CourseVO extends Course {

    private String gradeName;

    private String subjectName;
}
