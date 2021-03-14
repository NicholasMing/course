package cn.mallwe.course.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum SubjectEnum {
    CHINESE(6001, "语文"),
    MATHEMATICS(6002, "数学"),
    CHEMISTRY(6003, "化学"),
    PHYSICS(6004, "物理"),
    ENGLISH(6005, "英语"),
    BIOLOGY(6006, "生物");

    int value;
    String desc;

    SubjectEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public int getValue() {
        return this.value;
    }

    public static SubjectEnum stateOf(int status) {
        for (SubjectEnum subjectEnum : values()) {
            if ((int) subjectEnum.getValue() == status) {
                return subjectEnum;
            }
        }
        return null;
    }

    public static SubjectEnum descOf(String desc) {
        return getEnumList().stream().filter(o -> o.getDesc().equals(desc)).collect(Collectors.toList()).get(0);
    }

    public static List<SubjectEnum> getEnumList() {
        return Arrays.asList(values());
    }

}
