package site.euan.bester.service;

import site.euan.bester.domain.vo.CarouselTopVO;

import java.util.List;

public interface CarouselService {
    /**
     * 获取置顶轮播图信息
     * @return
     */
    List<CarouselTopVO> getTopList();
}
