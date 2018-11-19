package com.juzix.word.util;

import lombok.Data;

/**
 * @author jinx
 * @date 2018/11/15 17:44
 * Desc:返回结果包装类
 */
@Data
public class ResultWrapper<T> {

    private static final int CODE_OK = 200;
    private static final String MESSAGE = "OK";

    private Integer code;
    private String message;
    private T data;

    public ResultWrapper(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultWrapper(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResultWrapper buildSuccess() {
        return new ResultWrapper(CODE_OK, MESSAGE);
    }

    public static <T> ResultWrapper buildSuccess(T data) {
        return new ResultWrapper(CODE_OK, MESSAGE, data);
    }

    public static ResultWrapper buildError(int code, String message) {
        return new ResultWrapper(code, message);
    }
}
