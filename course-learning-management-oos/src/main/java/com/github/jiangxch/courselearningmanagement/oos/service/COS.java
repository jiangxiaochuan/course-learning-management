package com.github.jiangxch.courselearningmanagement.oos.service;

import com.github.jiangxch.courselearningmanagement.common.enums.ResultEnum;
import com.github.jiangxch.courselearningmanagement.common.exception.MyException;
import com.github.jiangxch.courselearningmanagement.common.result.Result;
import com.github.jiangxch.courselearningmanagement.common.utils.GenerateUtil;
import com.github.jiangxch.courselearningmanagement.oos.result.FileResult;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 腾讯COS的OSS服务
 * SDK文档: https://cloud.tencent.com/document/product/436/10199
 *
 * @author: sanjin
 * @date: 2020/1/6 下午9:16
 */
@Service
@Slf4j
public class COS implements Oos{
    // 1 初始化用户身份信息（secretId, secretKey）。
    @Value("${cos.secretId}")
    String secretId;
    @Value("${cos.secretKey}")
    String secretKey;
    @Value("${cos.bucketName}")
    String bucketName;
    // 文件对象上传后访问的url域名,域名+文件名称即为访问地址,刚刚上传后有一定延迟才可以访问
    @Value("${cos.domain:#{null}}")
    String domain;

    private Region region;
    private ClientConfig clientConfig;
    private COSClient cosClient;
    private Bucket bucket;

    @Override
    public Result<FileResult> upload(MultipartFile file) {
        FileResult result = new FileResult();
        // file.getOriginalFilename() -> avatar.png
        if (file == null || file.getOriginalFilename() == null) {
            return Result.newResult(ResultEnum.PARAM_ERROR);
        }
        String fileName = getFileName(file);
        if (fileName == null) {
            return Result.newError("无法获取到文件名");
        }
        String[] split = fileName.split("\\.");
        if (split.length < 2) {
            return Result.newError("文件名称不合法,必须包括文件后缀名,fileName=" + fileName);
        }

        StringBuilder fileNamePrefix = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if (i != split.length - 1) {
                fileNamePrefix.append(split[i]);
            }
        }
        String fileNameSuffix = split[split.length - 1];//文件格式
        try {
            String newFilename = GenerateUtil.generatorFileName() + "_" + fileNamePrefix + "." + fileNameSuffix;
            // 指定要上传到 COS 上对象键,对象键与文件名相同
            ObjectMetadata metadata = new ObjectMetadata();
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, newFilename, file.getInputStream(), metadata);
            cosClient.putObject(putObjectRequest);

            String url = domain + newFilename;
            result.setFileName(fileName);
            result.setUrl(url);
            result.setExt(fileNameSuffix);
        } catch (CosClientException | IOException e) {
            log.info("upload file faild, exception={}", e.getClass().getName() + ":" + e.getMessage());
            throw new MyException(ResultEnum.FILE_UPLOAD_FAILED);
        }
        return Result.newSuccess(result);
    }

    @PostConstruct
    private void init() {
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        region = new Region("ap-guangzhou");
        clientConfig = new ClientConfig(region);
        cosClient = new COSClient(cred, clientConfig);
        // get bucket
        List<Bucket> buckets = cosClient.listBuckets();
        for (Bucket buc : buckets) {
            if (bucketName != null && bucketName.equals(buc.getName())) {
                bucket = buc;
                break;
            }
        }
        log.info("Init tengxun COS,secretId={},secretKey={},bucketName={},domain={}",
                secretId, secretKey, bucketName, domain);
    }

    @PreDestroy
    private void destory() {
        // 关闭客户端(关闭后台线程)
        cosClient.shutdown();
    }

    public static void main(String[] args) {
        String fileName = "avatar.png";
        System.out.println(Arrays.toString(fileName.split("\\.")));
    }
}
