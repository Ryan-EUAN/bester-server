package site.euan.bester.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;

@Component
@Slf4j
public class TencentCosUtil {
    @Value("${tencent.cos.secretId}")
    private String secretId;
    @Value("${tencent.cos.secretKey}")
    private String secretKey;
    @Value("${tencent.cos.bucketName}")
    private String bucketName;
    @Value("${tencent.cos.region}")
    private String region;
    @Value("${tencent.cos.folder}")
    private String folder;

    public COSClient getCosClient() {
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region reg = new Region(region);
        ClientConfig clientConfig = new ClientConfig(reg);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        return new COSClient(cred, clientConfig);
    }

    // 上传文件
    public void uploadFile(String key, File file) {
        try {
            COSClient cosClient = getCosClient();
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
            cosClient.putObject(putObjectRequest);
        } catch (Exception e) {
            log.error("文件上传失败: {}", key, e);
        }
    }

    // 上传文件（InputStream版本）
    public String uploadFile(String key, InputStream inputStream, long contentLength) {
        try {
            COSClient cosClient = getCosClient();
            key = folder + key;
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(contentLength);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, metadata);
            cosClient.putObject(putObjectRequest);
            String fileUrl = "https://" + bucketName + ".cos." + region + ".myqcloud.com/" + key;
            return fileUrl;
        } catch (CosClientException e) {
            log.error("客户端异常: {}", e.getMessage());
            return null;
        } catch (Exception e) {
            log.error("文件上传失败: {}", key, e);
            return null;
        }
    }

    // 下载文件
    public InputStream downloadFile(String key) {
        try {
            COSClient cosClient = getCosClient();
            COSObject cosObject = cosClient.getObject(bucketName, key);
            log.info("文件下载成功: {}", key);
            return cosObject.getObjectContent();
        } catch (Exception e) {
            log.error("文件下载失败: {}", key, e);
            return null;
            // }
        }
    }

    // 删除文件
    public void deleteFile(String key) {
        try {
            COSClient cosClient = getCosClient();
            cosClient.deleteObject(bucketName, key);
            log.info("文件删除成功: {}", key);
        } catch (Exception e) {
            log.error("文件删除失败: {}", key, e);
        }
    }
}
