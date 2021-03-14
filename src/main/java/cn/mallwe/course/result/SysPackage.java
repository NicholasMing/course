package cn.mallwe.course.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SysPackage {

    @JsonProperty("package_id")
    private int packageId;
}
