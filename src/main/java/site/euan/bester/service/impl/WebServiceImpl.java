package site.euan.bester.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.euan.bester.domain.model.NavBarSecondInfo;
import site.euan.bester.domain.model.NavigationBarInfo;
import site.euan.bester.domain.vo.NavBarSecondInfoVO;
import site.euan.bester.domain.vo.NavigationBarInfoVO;
import site.euan.bester.mapper.NavBarSecondInfoMapper;
import site.euan.bester.mapper.NavigationBarInfoMapper;
import site.euan.bester.service.WebService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class WebServiceImpl implements WebService {
    @Resource
    private NavigationBarInfoMapper navigationBarInfoMapper;
    @Resource
    private NavBarSecondInfoMapper navBarSecondInfoMapper;

    @Override
    public List<NavigationBarInfoVO> getHeadInfos() {
        QueryWrapper<NavigationBarInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("nav_disable", true);
        queryWrapper.lambda().orderByAsc(NavigationBarInfo::getNavSort);
        List<NavigationBarInfo> navigationBarInfos = navigationBarInfoMapper.selectList(queryWrapper);
        ArrayList<NavigationBarInfoVO> list = new ArrayList<>();
        for (NavigationBarInfo nav : navigationBarInfos) {
            List<NavBarSecondInfoVO> navigationBarInfoVOS = new ArrayList<>();
            QueryWrapper<NavBarSecondInfo> qw = new QueryWrapper<>();
            qw.eq("nav_id", nav.getId());
            qw.eq("nav_disable", true);
            qw.lambda().orderByAsc(NavBarSecondInfo::getNavSort);
            List<NavBarSecondInfo> navBarSecondInfos = navBarSecondInfoMapper.selectList(qw);
            for (NavBarSecondInfo secondInfo : navBarSecondInfos) {
                NavBarSecondInfoVO build = NavBarSecondInfoVO.builder()
                        .label(secondInfo.getNavName())
                        .key(secondInfo.getId())
                        .path(secondInfo.getNavPath())
                        .build();
                navigationBarInfoVOS.add(build);
            }
            NavigationBarInfoVO navigationBarInfoVO = NavigationBarInfoVO.builder()
                    .label(nav.getNavName())
                    .key(nav.getId())
                    .icon(nav.getNavIcon())
                    .path(nav.getNavPath())
                    .children(navigationBarInfoVOS)
                    .build();
            list.add(navigationBarInfoVO);
        }
        return list;
    }
}
