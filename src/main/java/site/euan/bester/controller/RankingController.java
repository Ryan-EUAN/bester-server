package site.euan.bester.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.euan.bester.context.BaseContext;
import site.euan.bester.domain.entity.Result;
import site.euan.bester.domain.vo.TabsListInfoVO;
import site.euan.bester.service.UserInfoService;
import site.euan.bester.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author EUAN
 * 排行榜控制层
 */
@RestController
@RequestMapping("/ranking")
@Slf4j
public class RankingController {
    @Resource
    private UserService userService;
    @Resource
    private UserInfoService userInfoService;

    @GetMapping("/user/get/goldCoinList")
    public Result<List<TabsListInfoVO>> GetUserGoldCoinRankingList() {
        List<TabsListInfoVO> tabsListInfoVOList = userInfoService.getUserGoldCoinRankingList();
        return Result.success(tabsListInfoVOList);
    }
}