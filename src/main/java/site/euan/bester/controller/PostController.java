package site.euan.bester.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.euan.bester.context.BaseContext;
import site.euan.bester.domain.dto.CommentDTO;
import site.euan.bester.domain.dto.SendPostInfoDTO;
import site.euan.bester.domain.entity.Result;
import site.euan.bester.domain.model.Post;
import site.euan.bester.domain.vo.PostInfoVO;
import site.euan.bester.domain.vo.TabsPostListVO;
import site.euan.bester.exception.PostException;
import site.euan.bester.service.PostService;
import site.euan.bester.service.UserInfoService;
import site.euan.bester.utils.FileUtil;
import site.euan.bester.utils.TencentCosUtil;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/**
 * 帖子控制层
 * 该类处理与帖子相关的请求，包括发送评论和上传图片。
 */
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Api(tags = "帖子控制层")
@Slf4j
public class PostController {
    private final TencentCosUtil tencentCosUtil;
    private final FileUtil fileUtil;
    private final PostService postService;
    private final UserInfoService userInfoService;
    @Value("${file.maxSizeInMB:3}")
    private long maxSizeInMB;

    /**
     * 发送评论
     *
     * @return 返回结果，包含评论内容
     */
    @PostMapping("/send")
    @ApiOperation(value = "发送评论")
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
    @ApiOperation(value = "上传图片")
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
    @ApiOperation(value = "发布帖子")
    public Result<Post> publishBlog(@RequestBody SendPostInfoDTO sendPostInfoDTO) {
        log.info("发布帖子：{}", sendPostInfoDTO);
        Long currentId = BaseContext.getCurrentId();
        if (currentId == null) {
            throw new PostException("账号不存在，请登录在使用");
        }
        Post post = postService.publish(sendPostInfoDTO, currentId);
        userInfoService.publish(currentId);
        return Result.success(post);
    }

    @GetMapping("/list")
    @ApiOperation(value = "获取博客列表")
    public Result<List<PostInfoVO>> blogList() {
        List<PostInfoVO> postInfoVOList = postService.list();
        postInfoVOList.sort(Comparator.comparing(PostInfoVO::getId).reversed());
        return Result.success(postInfoVOList);
    }

    @PutMapping("/like")
    @ApiOperation(value = "点赞")
    public Result<?> BlogLike(@RequestParam String blogId) {
        Long currentId = BaseContext.getCurrentId();
        List<Long> likeList = postService.like(blogId, currentId);
        return Result.success(likeList);
    }

    @GetMapping("/test")
    @ApiOperation(value = "测试")
    public Result<?> test() {
        postService.test();
        return Result.success();
    }

    @GetMapping("/ranking/list")
    @ApiOperation(value = "获取帖子模块列表")
    public Result<List<TabsPostListVO>> List(@RequestParam("name") String name) {
        log.info("请求的是：{}", name);
        List<TabsPostListVO> tabsListInfoVOList = postService.rankingList(name);
        return Result.success(tabsListInfoVOList);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID获取帖子")
    public Result<?> getById(@PathVariable(value = "id") String path) {
        log.info("获取：{}", path);
        PostInfoVO postInfo = postService.getById(path);
        return Result.success(postInfo);
    }
}
