package site.euan.bester.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.euan.bester.domain.entity.Result;
import site.euan.bester.domain.vo.OnlineVO;
import site.euan.bester.domain.vo.PlateVO;
import site.euan.bester.service.ModuleService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/module")
@Api(tags = "模块控制层")
@Slf4j
public class ModuleController {
    @Resource
    private ModuleService moduleService;

    @GetMapping("/getModule")
    @ApiOperation(value = "获取模块全部信息")
    public Result<List<PlateVO>> GetModule() {
        log.info("获取模块信息");
        List<PlateVO> plateVOList = moduleService.getModule();
        return Result.success(plateVOList);
    }

    @GetMapping("/getOnline")
    @ApiOperation(value = "获取在线会员信息")
    public Result<List<OnlineVO>> GetOnline() {
        log.info("获取在线会员信息");
        List<OnlineVO> onlineVOList = new ArrayList<>();
        List<String> labels = Arrays.asList("管理员", "版主", "超级版主", "普通用户", "VIP", "隐身人数", "游客人数");
        for (String label : labels) {
            onlineVOList.add(OnlineVO.builder()
                    .label(label)
                    .value(new Random().nextInt(900) + 100)
                    .build());
        }
        return Result.success(onlineVOList);
    }
}
