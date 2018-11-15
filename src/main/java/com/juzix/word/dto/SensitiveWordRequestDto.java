package com.juzix.word.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author jinx
 * @date 2018/11/15 17:43
 * Desc:
 */
@Data
public class SensitiveWordRequestDto {
    @NotEmpty(message = "待脱敏字符串不能为空")
    private String txt;
}
