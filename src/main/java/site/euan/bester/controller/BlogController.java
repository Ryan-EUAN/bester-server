package site.euan.bester.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.euan.bester.context.BaseContext;
import site.euan.bester.domain.dto.CommentDTO;
import site.euan.bester.domain.dto.SendBlogInfoDTO;
import site.euan.bester.domain.entity.Result;
import site.euan.bester.domain.model.Blog;
import site.euan.bester.domain.vo.BlogInfoVO;
import site.euan.bester.service.BlogService;
import site.euan.bester.service.UserInfoService;
import site.euan.bester.utils.FileUtil;
import site.euan.bester.utils.TencentCosUtil;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/**
 * 博客控制层
 * 该类处理与博客相关的请求，包括发送评论和上传图片。
 */
@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
@Slf4j
public class BlogController {
    private final TencentCosUtil tencentCosUtil;
    private final FileUtil fileUtil;
    private final BlogService blogService;
    private final UserInfoService userInfoService;
    @Value("${file.maxSizeInMB:3}")
    private long maxSizeInMB;

    /**
     * 发送评论
     *
     * @return 返回结果，包含评论内容
     */
    @PostMapping("/send")
    public Result<String> sendComment(@RequestBody CommentDTO commentDTO) {
        log.info("发送评论{}", commentDTO);
        return Result.success();
    }

    /**
     * 上传图片
     *
     * @param file 上传的图片文件
     * @return 返回结果，包含上传状态信息
     */
    @PostMapping("/upload-image")
    public Result<String> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty() || !fileUtil.isImageFile(file)) {
            return Result.error("无效的文件或文件为空");
        }
        try {
            String uid = UUID.randomUUID().toString();
            long timestamp = System.currentTimeMillis();
            String originalFileName = file.getOriginalFilename();
            String newFileName = uid + "_" + timestamp + "_" + uid + originalFileName;
            String fileUrl = tencentCosUtil.uploadFile(newFileName, file.getInputStream(), file.getSize());
            return Result.success(fileUrl);
        } catch (IOException e) {
            log.error("文件上传失败", e);
            return Result.error("文件上传失败");
        }
    }

    @PostMapping("/publish")
    public Result<Blog> publishBlog(@RequestBody SendBlogInfoDTO sendBlogInfoDTO) {
        Long currentId = BaseContext.getCurrentId();
        Blog blog = blogService.publish(sendBlogInfoDTO, currentId);
        userInfoService.publish(currentId);
        return Result.success(blog);
    }

    @GetMapping("/list")
    public Result<List<BlogInfoVO>> blogList() {
        List<BlogInfoVO> blogInfoVOList = blogService.list();
        blogInfoVOList.sort(Comparator.comparing(BlogInfoVO::getId).reversed());
        return Result.success(blogInfoVOList);
    }

    @PutMapping("/like")
    public Result<?> BlogLike(@RequestParam String blogId) {
        Long currentId = BaseContext.getCurrentId();
        List<Long> likeList = blogService.like(blogId, currentId);
        return Result.success(likeList);
    }

    @GetMapping("/test")
    public Result<?> test() {
        blogService.test();
        return Result.success();
    }
}
