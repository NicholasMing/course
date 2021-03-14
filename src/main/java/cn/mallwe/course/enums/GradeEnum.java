package cn.mallwe.course.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum GradeEnum {
    FIRST(7001, "一年级"),
    SECOND(7002, "二年级"),
    Third(7003, "三年级"),
    FOURTH(7004, "四年级"),
    FIFTH(7005, "五年级"),
    SIXTH(7006, "六年级"),
    SEVEN(6001, "初一"),
    EIGHTH(6002, "初二"),
    NINTH(6003, "初三"),
    SENIOR_ONE(5001, "高一"),
    SENIOR_TWO(5002, "高二"),
    SENIOR_THREE(5003, "高三");

    int value;
    String desc;

    GradeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public int getValue() {
        return this.value;
    }

    public static GradeEnum stateOf(int status) {
        for (GradeEnum gradeEnum : values()) {
            if ((int) gradeEnum.getValue() == status) {
                return gradeEnum;
            }
        }
        return null;
    }

    public static GradeEnum descOf(String desc) {
        return getEnumList().stream().filter(o -> o.getDesc().equals(desc)).collect(Collectors.toList()).get(0);
    }

    public static List<GradeEnum> getEnumList() {
        return Arrays.asList(values());
    }

}
