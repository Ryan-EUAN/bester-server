package site.euan.bester.service;

import site.euan.bester.domain.model.UserInfo;
import site.euan.bester.domain.vo.TabsListInfoVO;

import java.util.List;

public interface UserInfoService {
    UserInfo addInfo(UserInfo userInfo);
    /**
     * 根据账号ID查询用户信息
     * @param id
     * @return
     */
    UserInfo getByUserId(Long id);

    /**
     * 根据ID查询用户信息
     * @param id
     * @return
     */
    UserInfo getById(Long id);

    /**
     * 添加博客数量
     */
    void publish(Long currentId);

    /**
     * 获取用户金币排行榜
     * @return
     */
    List<TabsListInfoVO> getUserGoldCoinRankingList();
}
