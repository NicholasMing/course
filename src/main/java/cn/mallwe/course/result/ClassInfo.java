package cn.mallwe.course.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


/**
 * 班级信息
 */
@Data
public class ClassInfo {

    @JsonProperty("classid")
    private int classId;

    /**
     * 学生数量
     */
    @JsonProperty("student_total")
    private int studentTotal;

    /**
     * 实际申请数量
     */
    @JsonProperty("real_total_apply")
    private int realTotalApply;

    /**
     * 辅导老师
     */
    @JsonProperty("tu_list")
    private List<Teacher> tutorList;
}
