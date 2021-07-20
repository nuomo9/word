package com.example.word.vo;

/**
 * 统一响应
 *
 * @author liuyuhui
 */
public class Result<T> {
    private static final Integer SUCCESS_CODE = 200;
    private static final Integer FAILURE_CODE = 200;
    private static final Integer ERROR_CODE = 500;
    public static final String SUCCESS_STATUS = "success";
    public static final String FAILURE_STATUS = "failure";
    public static final String ERROR_STATUS = "error";

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态说明
     */
    private String status;

    /**
     * 返回实体
     */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Result success() {
        Result result = new Result<>();
        result.setCode(SUCCESS_CODE);
        result.setStatus(SUCCESS_STATUS);
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setStatus(SUCCESS_STATUS);
        result.setData(data);
        return result;
    }

    public static Result failure() {
        Result result = new Result();
        result.setCode(FAILURE_CODE);
        result.setStatus(FAILURE_STATUS);
        return result;
    }

    public static <T> Result<T> failure(T data) {
        Result<T> result = new Result<>();
        result.setCode(FAILURE_CODE);
        result.setStatus(FAILURE_STATUS);
        result.setData(data);
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setCode(ERROR_CODE);
        result.setStatus(ERROR_STATUS);
        return result;
    }

    public static <T> Result<T> error(T data) {
        Result<T> result = new Result<>();
        result.setCode(ERROR_CODE);
        result.setStatus(ERROR_STATUS);
        result.setData(data);
        return result;
    }
}
