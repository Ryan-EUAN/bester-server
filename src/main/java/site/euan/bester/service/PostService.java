package site.euan.bester.service;

import site.euan.bester.domain.dto.SendPostInfoDTO;
import site.euan.bester.domain.model.Post;
import site.euan.bester.domain.vo.PostInfoVO;
import site.euan.bester.domain.vo.TabsPostListVO;

import java.util.List;

public interface PostService {
    /**
     * 获取博客全部信息
     * @return
     */
    List<PostInfoVO> list();

    /**
     * 发表博客
     * @param sendPostInfoDTO
     * @param currentId
     */
    Post publish(SendPostInfoDTO sendPostInfoDTO, Long currentId);

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
    PostInfoVO getById(String blogId);

    /**
     * 用于测试
     */
    void test();

    /**
     * 获取帖子板块排行
     * @param name
     * @return
     */
    List<TabsPostListVO> rankingList(String name);
}
