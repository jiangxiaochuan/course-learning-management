package com.github.jiangxch.courselearningmanagement.test.oos;

import com.github.jiangxch.courselearningmanagement.common.result.Result;
import com.github.jiangxch.courselearningmanagement.oos.result.FileResult;
import com.github.jiangxch.courselearningmanagement.oos.service.Oos;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author: sanjin
 * @date: 2020/2/26 下午7:36
 */
@Slf4j
public class CosTest extends BaseTest {
    @Autowired(required = false)
    private Oos oos;

    @Test
    public void uploadTest() throws IOException {
        File f = new File("/home/lanlan/Programming/Java/Project/School/course-learning-management/doc/img/1.jpg");
        FileInputStream fis = new FileInputStream(f);
        ByteArrayOutputStream baos = new ByteArrayOutputStream((int) f.length());
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = fis.read(buffer)) != -1) {
            baos.write(buffer,0, length);
        }

        MultipartFile mockMultipartFile = new MockMultipartFile(f.getName(), baos.toByteArray());
        Result<FileResult> result = oos.upload(mockMultipartFile);
        Assert.assertTrue(result.hasSuccess());
        log.info("result={}", result);
    }
}
