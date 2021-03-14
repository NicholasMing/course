package cn.mallwe.course.result;

import lombok.Getter;
import lombok.Setter;

public class RestResponse<T> {


    public static final Integer STATUS_OK = 200;

    public static final Integer STATUS_FAILED = 500;

    /**
     * 1、状态标识 200：成功，500 服务器异常
     */
    @Setter
    @Getter
    private int status;

    /**
     * 1.描述,例如:失败返回失败描述
     */
    @Setter
    @Getter
    private String message;

    /**
     * 自定义业务格式
     */
    @Setter
    @Getter
    private T data;

    public RestResponse() {
    }

    public RestResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static RestResponse buildResult() {
        RestResponse response = new RestResponse(STATUS_OK, "success");
        return response;
    }

    public static RestResponse buildResult(Object data) {
        RestResponse response = new RestResponse();
        if (data == null) {
            response.setMessage("找不到数据");
        } else {
            response.setData(data);
        }
        return response;
    }

    public static RestResponse buildResult(boolean isSuccess) {
        RestResponse response = null;
        if (isSuccess) {
            response = new RestResponse(STATUS_OK, "success");
        } else {
            response = new RestResponse(STATUS_FAILED, "failed");
        }
        return response;
    }

    public static RestResponse buildResult(Integer status, String message) {
        RestResponse response = new RestResponse(status, message);
        return response;
    }

    public static RestResponse buildResult(Integer status, String message, Object data) {
        RestResponse response = new RestResponse(status, message);
        response.setData(data);
        return response;
    }

}
