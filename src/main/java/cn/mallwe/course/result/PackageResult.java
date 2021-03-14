package cn.mallwe.course.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PackageResult {

    /**
     * 系统课
     */
    @JsonProperty("sys_package")
    private List<SysPackage> sysPackage;
}
