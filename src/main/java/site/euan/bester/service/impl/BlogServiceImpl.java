package site.euan.bester.service.impl;

import com.mongodb.client.MongoClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.euan.bester.domain.dto.SendBlogInfoDTO;
import site.euan.bester.domain.model.Blog;
import site.euan.bester.domain.vo.BlogInfoVO;
import site.euan.bester.repository.BlogRepository;
import site.euan.bester.service.BlogService;
import site.euan.bester.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author EUAN
 * 博客接口实现类
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    private final UserService userService;

    @Override
    public List<BlogInfoVO> list() {
        List<Blog> blogs = blogRepository.findAll();
        List<BlogInfoVO> blogInfoVOS = new ArrayList<>();
        for (Blog blog : blogs) {
            BlogInfoVO blogInfoVO = BlogInfoVO.builder()
                    .id(blog.getId())
                    .author(userService.getCurrent(blog.getSendId()))
                    .content(blog.getConnect())
                    .likes(blog.getLikes())
                    .comments(blog.getComments())
                    .reposts(blog.getReposts())
                    .images(blog.getUrls())
                    .time(blog.getTime())
                    .build();
            blogInfoVOS.add(blogInfoVO);
        }
        return blogInfoVOS;
    }

    @Override
    public Blog publish(SendBlogInfoDTO sendBlogInfoDTO, Long currentId) {
        Blog blog = new Blog();
        blog.setConnect(sendBlogInfoDTO.getConnect());
        blog.setUrls(sendBlogInfoDTO.getUrls());
        blog.setSendId(currentId);
        blog.setLikes(new ArrayList<>());
        blog.setReposts(0);
        blog.setTime(LocalDateTime.now());
        blog.setComments(new ArrayList<>());
        return blogRepository.save(blog);
    }

    @Override
    public List<Long> like(String blogId, Long currentId) {
        Blog blog = getById(blogId);
        if (blog.getLikes().contains(currentId)) {
            blog.getLikes().remove(currentId);
        } else {
            blog.getLikes().add(currentId);
        }
        blogRepository.save(blog);
        return blog.getLikes();
    }

    @Override
    public Blog getById(String id) {
        return blogRepository.findById(id).get();
    }

    @Override
    public void test() {

    }
}
