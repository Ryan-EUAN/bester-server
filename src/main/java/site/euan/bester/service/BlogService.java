package site.euan.bester.service;

import site.euan.bester.domain.dto.SendBlogInfoDTO;
import site.euan.bester.domain.model.Blog;
import site.euan.bester.domain.vo.BlogInfoVO;

import java.util.List;

public interface BlogService {
    /**
     * 获取博客全部信息
     * @return
     */
    List<BlogInfoVO> list();

    /**
     * 发表博客
     * @param sendBlogInfoDTO
     * @param currentId
     */
    Blog publish(SendBlogInfoDTO sendBlogInfoDTO, Long currentId);

    /**
     * 点赞
     * @param blogId
     * @param currentId
     */
    List<Long> like(String blogId, Long currentId);

    /**
     * 根据ID查询博客信息
     * @param blogId
     * @return
     */
    Blog getById(String blogId);

    /**
     * 用于测试
     */
    void test();
}
