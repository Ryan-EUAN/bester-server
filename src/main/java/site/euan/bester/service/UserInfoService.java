package site.euan.bester.service;

import site.euan.bester.domain.dto.ProfileDTO;
import site.euan.bester.domain.dto.VerifyIdentityDTO;
import site.euan.bester.domain.model.UserInfo;
import site.euan.bester.domain.vo.TabsListInfoVO;
import site.euan.bester.domain.vo.VerifyVO;

import java.util.List;

public interface UserInfoService {
    UserInfo addInfo(UserInfo userInfo);

    /**
     * 根据账号ID查询用户信息
     *
     * @param id
     * @return
     */
    UserInfo getByUserId(Long id);

    /**
     * 根据ID查询用户信息
     *
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
     *
     * @return
     */
    List<TabsListInfoVO> getUserGoldCoinRankingList();

    /**
     * 更新用户资料的方法。
     *
     * @param currentId  当前用户的ID，用于确定要更新哪个用户的资料。
     * @param profileDTO 包含更新后用户资料的DTO（数据传输对象），用于传递更新后的数据。
     * @return
     */
    Long updateProfile(Long currentId, ProfileDTO profileDTO);

    /**
     * 验证身份的方法。
     * <p>
     * 该方法接收一个VerifyIdentityDTO类型的参数，用于验证用户的身份信息。
     *
     * @param currentId
     * @param verifyIdentityDTO 包含用户身份信息的DTO对象。
     */
    void verifyIdentity(Long currentId, VerifyIdentityDTO verifyIdentityDTO);

    /**
     * 获取认证信息。
     *
     * @param currentId 当前用户的ID。
     * @return 返回一个VerifyVO对象，该对象包含了验证信息。
     */
    VerifyVO getVerify(Long currentId);
}
