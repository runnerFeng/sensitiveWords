package com.juzix.word.Interceptor;

import com.juzix.word.util.ResultWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * @author jinx
 * @date 2018/9/17 11:28
 * Desc:参数校验异常处理器
 */
@RestControllerAdvice
public class ValidatedExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class, BindException.class,
            MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public Object methodArgumentNotValidException(Exception c) {
        String errMsg = null;
        IllegalArgumentException ia = null;
        BindException be = null;
        MethodArgumentNotValidException man = null;
        ConstraintViolationException cve = null;
        if (c instanceof IllegalArgumentException) {
            ia = (IllegalArgumentException) c;
        } else if (c instanceof BindException) {
            be = (BindException) c;
        } else if (c instanceof MethodArgumentNotValidException) {
            man = (MethodArgumentNotValidException) c;
        } else if (c instanceof ConstraintViolationException) {
            cve = (ConstraintViolationException) c;
        } else {
            errMsg = "10008";
        }

        if (ia != null) {
            errMsg = ia.getMessage();
        }

        if (be != null) {
            errMsg = getErrMsg(be.getBindingResult());
            return ResultWrapper.buildError(-1, errMsg);
        }

        if (man != null) {
            errMsg = getErrMsg(man.getBindingResult());
            return ResultWrapper.buildError(-1, errMsg);
        }

        if (cve != null) {
            Set<ConstraintViolation<?>> violations = cve.getConstraintViolations();
            StringBuilder builder = new StringBuilder("");
            for (ConstraintViolation<?> violation : violations) {
                builder.append(violation.getMessage()).append(";");
            }

            errMsg = StringUtils.stripEnd(builder.toString(), ";");
            return ResultWrapper.buildError(-1, errMsg);
        }
        return ResultWrapper.buildSuccess();
    }

    /**
     * 数据转换类型错误
     *
     * @param c
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public Object methodNumberFormatException(MethodArgumentTypeMismatchException c) {
        StringBuilder sb = new StringBuilder();
        sb.append(";");
        sb.append("字段：").append(c.getName()).append(";");
        sb.append("值:").append(c.getValue());
        return ResultWrapper.buildError(-1, sb.toString());
    }

    @ExceptionHandler(value = {Exception.class})
    public Object methodException(Exception c) {
        c.printStackTrace();
        return ResultWrapper.buildError(-1, null);
    }

    private String getErrMsg(BindingResult br) {
        String errMsg = null;
        List<ObjectError> errors = br.getAllErrors();
        StringBuilder builder = new StringBuilder("");
        for (ObjectError error : errors) {
            if (!errors.isEmpty() && errors.size() > 0) {
                errMsg = errors.get(0).getDefaultMessage();
                builder.append(errMsg).append(";");
            }
        }
        return StringUtils.stripEnd(builder.toString(), ";");
    }

}
