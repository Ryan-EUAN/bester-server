package site.euan.bester.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import site.euan.bester.domain.dto.SendPostInfoDTO;
import site.euan.bester.domain.model.Post;
import site.euan.bester.domain.vo.PostInfoVO;
import site.euan.bester.domain.vo.TabsPostListVO;
import site.euan.bester.domain.vo.UserInfoVO;
import site.euan.bester.exception.PostException;
import site.euan.bester.service.PostService;
import site.euan.bester.service.UserService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author EUAN
 * 博客接口实现类
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class PostServiceImpl implements PostService {
    private final UserService userService;
    private final MongoTemplate mongoTemplate;

    @Override
    public List<PostInfoVO> list() {
        List<Post> posts = mongoTemplate.findAll(Post.class);
        List<PostInfoVO> postInfoVOS = new ArrayList<>();
        for (Post post : posts) {
            PostInfoVO postInfoVO = PostInfoVO.builder()
                    .id(post.getId())
                    .author(userService.getCurrent(post.getSendId()))
                    .content(post.getContent())
                    .likes(post.getLikes())
                    .comments(post.getComments())
                    .reposts(post.getReposts())
                    .images(post.getImages())
                    .time(post.getTime())
                    .build();
            postInfoVOS.add(postInfoVO);
        }
        return postInfoVOS;
    }

    @Override
    public Post publish(SendPostInfoDTO sendPostInfoDTO, Long currentId) {
        Post post = new Post();
        BeanUtils.copyProperties(sendPostInfoDTO, post);
        post.setSendId(currentId);
        post.setLikes(new ArrayList<>());
        post.setReposts(0);
        post.setTime(LocalDateTime.now());
        post.setComments(new ArrayList<>());
        return mongoTemplate.insert(post);
    }

    @Override
    public List<Long> like(String blogId, Long currentId) {
        PostInfoVO post = getById(blogId);
        if (post.getLikes().contains(currentId)) {
            post.getLikes().remove(currentId);
        } else {
            post.getLikes().add(currentId);
        }
        mongoTemplate.save(post);
        return post.getLikes();
    }

    @Override
    public PostInfoVO getById(String id) {
        Post post = mongoTemplate.findById(id, Post.class);
        if (post == null) {
            throw new PostException("帖子不存在");
        }
        return PostInfoVO.builder()
                .id(post.getId())
                .author(userService.getCurrent(post.getSendId()))
                .title(post.getTitle())
                .content(post.getContent())
                .likes(post.getLikes())
                .comments(post.getComments())
                .reposts(post.getReposts())
                .images(post.getImages())
                .time(post.getTime())
                .build();
    }

    @Override
    public void test() {

    }

    @Override
    public List<TabsPostListVO> rankingList(String name) {
        List<TabsPostListVO> tabsPostListVOS = new ArrayList<>();
        List<Post> posts = mongoTemplate.findAll(Post.class);
        posts.sort(Comparator.comparing(Post::getTime).reversed());
        posts = posts.stream().limit(10).collect(Collectors.toList());
        if (Objects.equals(name, "latest_theme")) {
            //最新主题
            posts.forEach(post -> tabsPostListVOS.add(TabsPostListVO.builder()
                    .id(post.getId())
                    .label(post.getTitle())
                    .end(post.getTime())
                    .build()));
        }
        log.info("数据：{}", tabsPostListVOS);
        return tabsPostListVOS;
    }
}
