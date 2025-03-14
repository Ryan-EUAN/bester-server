package site.euan.bester.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.euan.bester.domain.entity.Result;
import site.euan.bester.domain.vo.NavigationBarInfoVO;
import site.euan.bester.service.WebService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 集中处理页面传过来的数据
 *
 * @author EUAN
 * @version 0.0.1
 */
@RestController
@RequestMapping("/web")
@Slf4j
public class WebController {
    @Resource
    private WebService webService;

    @GetMapping("/get_head_info")
    public Result<List<NavigationBarInfoVO>> GetHeadInfos() {
        List<NavigationBarInfoVO> navigationBarInfoVOS = webService.getHeadInfos();
        return Result.success(navigationBarInfoVOS);
    }
}
