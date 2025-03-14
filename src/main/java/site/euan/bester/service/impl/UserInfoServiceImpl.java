package site.euan.bester.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.euan.bester.domain.model.NavBarSecondInfo;
import site.euan.bester.domain.model.UserInfo;
import site.euan.bester.domain.vo.TabsListInfoVO;
import site.euan.bester.mapper.UserInfoMapper;
import site.euan.bester.service.UserInfoService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo addInfo(UserInfo userInfo) {
        int insert = userInfoMapper.insert(userInfo);
        return userInfo;
    }

    @Override
    public UserInfo getByUserId(Long id) {
        log.info("拿到的ID{}", id);
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        return userInfoMapper.selectOne(queryWrapper);
    }

    @Override
    public UserInfo getById(Long id) {
        return userInfoMapper.selectById(id);
    }

    @Override
    public void publish(Long currentId) {
        UserInfo userInfo = getById(currentId);
        log.info("拿一下个人信息{}", userInfo);
        userInfo.setPosts(userInfo.getPosts() + 1);
        userInfoMapper.updateById(userInfo);
    }

    @Override
    public List<TabsListInfoVO> getUserGoldCoinRankingList() {
        QueryWrapper<UserInfo> qw = new QueryWrapper<>();
        qw.lambda().orderByAsc(UserInfo::getGoldCoin);
        List<UserInfo> userInfos = userInfoMapper.selectList(qw);
        List<TabsListInfoVO> tabsListInfoVOList = new ArrayList<>();
        userInfos.forEach(a -> tabsListInfoVOList.add(TabsListInfoVO.builder()
                .id(a.getId())
                .label(a.getName())
                .end(a.getGoldCoin().toString())
                .build()));
        return tabsListInfoVOList;
    }
}
