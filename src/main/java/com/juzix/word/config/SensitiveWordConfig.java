package com.juzix.word.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author jinx
 * @date 2018/11/15 17:25
 * Desc:
 */
@Data
@ConfigurationProperties(prefix = "sensitive")
@Component
public class SensitiveWordConfig {
    /**
     * 文件路径 eg:/home/juzix/file
     */
    private String filePath;
    /**
     * 文件名称 eg:sensitiveWord.txt
     */
    private String fileName;
    /**
     * 替换字符 eg:*
     */
    private String replaceText;

}
