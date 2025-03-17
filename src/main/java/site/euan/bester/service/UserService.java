package site.euan.bester.service;

import site.euan.bester.domain.dto.ProfileDTO;
import site.euan.bester.domain.dto.UserEmailLoginDTO;
import site.euan.bester.domain.dto.UserLoginDTO;
import site.euan.bester.domain.dto.UserRegisterDTO;
import site.euan.bester.domain.model.User;
import site.euan.bester.domain.vo.ProfileVO;
import site.euan.bester.domain.vo.TabsListInfoVO;
import site.euan.bester.domain.vo.UserInfoVO;

import java.util.List;

/**
 * @author EUAN
 * 用户接口
 */
public interface UserService {
    /**
     * 用户登录
     *
     * @param userLoginDTO
     * @return
     */
    UserInfoVO login(UserLoginDTO userLoginDTO);

    /**
     * 用户注册
     *
     * @param userRegisterDTO
     * @return
     */
    UserInfoVO register(UserRegisterDTO userRegisterDTO);

    /**
     * 获取当前用户信息
     *
     * @param id
     * @return
     */
    UserInfoVO getCurrent(Long id);

    /**
     * 修改当前用户密码
     *
     * @param password
     * @param currentId
     */
    User changePassword(String password, Long currentId);

    /**
     * 退出登录
     *
     * @param id
     */
    void logout(Long id);

    /**
     * 邮箱登录
     *
     * @param userEmailLoginDTO
     * @return
     */
    UserInfoVO loginEmail(UserEmailLoginDTO userEmailLoginDTO);

    /**
     * 获取用户个人资料
     *
     * @param currentId
     * @return
     */
    ProfileVO getProfile(Long currentId);

    /**
     * 更新用户资料的方法。
     *
     * @param currentId  当前用户的ID，用于确定要更新哪个用户的资料。
     * @param profileDTO 包含更新后的用户资料的数据传输对象（DTO），包含了用户的基本信息。
     */
    void updateProfile(Long currentId, ProfileDTO profileDTO);
}
