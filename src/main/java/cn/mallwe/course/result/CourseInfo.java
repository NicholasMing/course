package cn.mallwe.course.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 课程信息
 */
@Data
public class CourseInfo {

    private Integer cid;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 课程时间计划
     */
    @JsonProperty("time_plan")
    private String timePlan;

    /**
     * 年级
     */
    private int grade;

    /**
     * 学科
     */
    private int subject;

    /**
     * 优惠后价格(单位：分)
     */
    @JsonProperty("af_amount")
    private int afAmount;

    /**
     * 班级信息
     */
    @JsonProperty("class_info")
    private ClassInfo classInfo;

    /**
     * 教师信息
     */
    @JsonProperty("te_list")
    private List<Teacher> teList;
}
