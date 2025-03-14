package site.euan.bester.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import site.euan.bester.domain.model.UserInfo;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
