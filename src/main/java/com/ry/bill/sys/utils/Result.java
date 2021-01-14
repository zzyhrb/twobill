package com.ry.bill.sys.utils;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author CKFuture
 * @since 2020-12-24 20:52
 *
 */
@Data
public class Result  implements Serializable {
    private static final long serialVersionUID = -3948389268046368059L;

    private String code;

    private String msg;

    private Object data;

    private Integer count;

    public Result() {
    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public static Result success(String msg) {
        Result Result = new Result();
        Result.setCode(ResponseCode.SUCCESS);
        Result.setMsg(msg);
        return Result;
    }

    public static Result success(Object data) {
        Result Result = new Result();
        Result.setCode(ResponseCode.SUCCESS);
        Result.setData(data);

        try {
            Result.setCount(((List<?>) data).size());
        } catch (Exception e) {
            Result.setCount(0);
        }

        return Result;
    }

    public static Result failure(String code, String msg) {
        Result Result = new Result();
        Result.setCode(code);
        Result.setMsg(msg);
        return Result;
    }

    public static Result failure(String code, String msg, Object data) {
        Result Result = new Result();
        Result.setCode(code);
        Result.setMsg(msg);
        Result.setData(data);
        return Result;
    }
}
