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
    private String filePath;
    private String replaceText;
}
