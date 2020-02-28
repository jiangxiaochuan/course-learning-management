package com.github.jiangxch.courselearningmanagement.provider.service;

import com.github.jiangxch.courselearningmanagement.common.result.Result;
import com.github.jiangxch.courselearningmanagement.common.utils.JsonUtil;
import com.github.jiangxch.courselearningmanagement.providerapi.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author: sanjin
 * @date: 2020/2/28 下午1:46
 */
@Service
@Slf4j
public class SystemServiceImpl implements SystemService, InitializingBean {
    private List<String> chinaUniversityNameData;

    @Override
    public Result<List<String>> listChinaUniversityNames() {
        return Result.newSuccess(chinaUniversityNameData);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        chinaUniversityNameData = readChinaUniversityNameData();
    }

    private List<String> readChinaUniversityNameData() {
        Resource resource = new ClassPathResource("SchoolNameData.json");
        String content = "";
        try(InputStream is = resource.getInputStream()) {
            byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
            content = new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> result = JsonUtil.jsonToList(content, String.class);
        log.info("success read 【SchoolNameData.json】 file, num={}", result.size());
        return result;
    }
}
