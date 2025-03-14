package site.euan.bester.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author EUAN
 * 文件工具类
 */
@Component
public class FileUtil {
    /**
     * 检查文件是否为图片类型
     *
     * @param file 上传的文件
     * @return 如果是图片文件返回true，否则返回false
     */
    public boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }
}
