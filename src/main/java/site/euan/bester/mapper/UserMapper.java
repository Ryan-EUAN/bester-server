package site.euan.bester.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import site.euan.bester.domain.model.User;

/**
 * @author EUAN
 * 用户Mapper接口
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM tb_user WHERE username=#{username}")
    User geyByAccount(String username);

    int register(User user);

    @Update("UPDATE tb_user SET is_online=0 WHERE id=#{userId}")
    void logOut(Long userId);
}
