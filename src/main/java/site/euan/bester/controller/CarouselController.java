package site.euan.bester.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.euan.bester.domain.entity.Result;
import site.euan.bester.domain.vo.CarouselTopVO;
import site.euan.bester.service.CarouselService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/carousel")
@Api(tags = "轮播图控制层")
@Slf4j
public class CarouselController {
    @Resource
    private CarouselService carouselService;

    @GetMapping("/top")
    @ApiOperation(value = "获取置顶轮播图信息")
    public Result<List<CarouselTopVO>> GetCarouseTop() {
        log.info("获取置顶轮播图方法已执行...");
        List<CarouselTopVO> carouselTopVOList = carouselService.getTopList();
        return Result.success(carouselTopVOList);
    }
}
