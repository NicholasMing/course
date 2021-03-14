package cn.mallwe.course.entity;

import lombok.Data;

@Data
public class Course {

    private int cid;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 课程时间计划
     */
    private String timePlan;

    /**
     * 学科
     */
    private int subject;


    /**
     * 年级
     */
    private int grade;

    /**
     * 优惠后价格(单位：分)
     */
    private int afAmount;

    /**
     * 学生数量
     */
    private int studentTotal;

    /**
     * 实际申请数量
     */
    private int realTotalApply;

    /**
     * 教师信息
     */
    private String teacherName;

    /**
     * 辅导老师信息
     */
    private String tutorName;
}
