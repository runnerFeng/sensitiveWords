package com.juzix.word.controller;

import com.juzix.word.config.SensitiveWordConfig;
import com.juzix.word.dto.SensitiveWordRequestDto;
import com.juzix.word.util.ResultWrapper;
import com.juzix.word.util.SensitiveWordUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Set;

/**
 * @author jinx
 * @date 2018/11/15 16:59
 * Desc:
 */
@Controller
@RestController
public class SensitiveWordFilterController {

    @Resource
    private SensitiveWordConfig config;

    @RequestMapping(value = "sensitiveWordFilter", method = RequestMethod.POST)
    public Object sensitiveWordFilter(@RequestBody @Validated SensitiveWordRequestDto dto) {
        // 加载敏感词库
        Set<String> set;
        try {
//            set = SensitiveWordUtil.loadSensitiveWordByFilePath(config.getFilePath());
            set = SensitiveWordUtil.loadSensitiveWordByFileName(config.getFileName());
        } catch (Exception e) {
            return ResultWrapper.buildError(-1, "敏感词库不存在！");
        }
        // 初始化敏感词库
        SensitiveWordUtil.init(set);
        String filterString = SensitiveWordUtil.replaceSensitiveWord(dto.getTxt().replaceAll(" +",""), config.getReplaceText());
        return ResultWrapper.buildSuccess(filterString);
    }

    public static void main(String[] args) throws IOException {
        Set<String> set = SensitiveWordUtil.loadSensitiveWordByFileName("sensitiveWord.txt");

    }

}
