package site.euan.bester.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.euan.bester.domain.model.CarouselTopInfo;
import site.euan.bester.domain.vo.CarouselTopVO;
import site.euan.bester.mapper.CarouseTopInfoMapper;
import site.euan.bester.service.CarouselService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CarouselServiceImpl implements CarouselService {
    @Resource
    private CarouseTopInfoMapper carouseTopInfoMapper;

    @Override
    public List<CarouselTopVO> getTopList() {
        List<CarouselTopInfo> carouselTopInfos = carouseTopInfoMapper.selectList(null);
        return carouselTopInfos.stream().map(a -> CarouselTopVO.builder()
                        .imageUrl(a.getImgUrl())
                        .title(a.getTitle())
                        .description(a.getDescription())
                        .link(a.getPath())
                        .build())
                .collect(Collectors.toList());
    }
}
