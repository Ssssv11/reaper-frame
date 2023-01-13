package com.ssssv.bean;

import lombok.Data;

import java.io.Serializable;

@Data
@SuppressWarnings("all")
public class Result<T> implements Serializable {

    private Boolean success;

    private Integer code;

    private String message;

    private T data;

    private Result() {
    }

    public static Result ok() {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage(ResultMessage.SUCCESS);
        return result;
    }

    public static Result ok(Integer resultCode, String resultMessage) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(resultCode);
        result.setMessage(resultMessage);
        return result;
    }

    public static <T> Result ok(Integer resultCode, String resultMessage, T data) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(resultCode);
        result.setMessage(resultMessage);
        result.setData(data);
        return result;
    }

    public static <T> Result ok(T data) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage(ResultMessage.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.ERROR);
        result.setMessage(ResultMessage.ERROR);
        return result;
    }

    public static Result error(Integer resultCode, String resultMessage) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(resultCode);
        result.setMessage(resultMessage);
        return result;
    }

    public static <T> Result error(Integer resultCode, String resultMessage, T data) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(resultCode);
        result.setMessage(resultMessage);
        result.setData(data);
        return result;
    }

    public static <T> Result error(T data) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.ERROR);
        result.setMessage(ResultMessage.ERROR);
        result.setData(data);
        return result;
    }
}
