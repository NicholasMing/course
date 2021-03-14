package cn.mallwe.course.query;

import lombok.Data;

@Data
public class CourseQuery {

    /**
     * 学科
     */
    private Integer subject;

    /**
     * 年级
     */
    private Integer grade;

    private int pageNum;

    private int pageSize;
}
