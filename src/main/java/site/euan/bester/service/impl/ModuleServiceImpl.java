package site.euan.bester.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import site.euan.bester.domain.model.Plate;
import site.euan.bester.domain.model.PlateInfo;
import site.euan.bester.domain.vo.PlateInfoVO;
import site.euan.bester.domain.vo.PlateVO;
import site.euan.bester.mapper.PlateInfoMapper;
import site.euan.bester.mapper.PlateMapper;
import site.euan.bester.service.ModuleService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Resource
    private PlateMapper plateMapper;
    @Resource
    private PlateInfoMapper plateInfoMapper;

    @Override
    public List<PlateVO> getModule() {
        List<Plate> plates = plateMapper.selectList(new QueryWrapper<Plate>().eq("enable", true));
        return plates.stream().map(plate -> {
            List<PlateInfoVO> plateInfoVOS = plateInfoMapper.selectList(new QueryWrapper<PlateInfo>().eq("plate_id", plate.getId())).stream()
                    .map(plateInfo -> PlateInfoVO.builder()
                            .name(plateInfo.getPlateName())
                            .icon(plateInfo.getPlateIcon())
                            .path(plateInfo.getPlatePath())
                            .count("0")
                            .posts("0")
                            .lastPost("1小时前")
                            .topics("0")
                            .build())
                    .collect(Collectors.toList());
            return PlateVO.builder()
                    .title(plate.getTitle())
                    .plateInfos(plateInfoVOS)
                    .build();
        }).collect(Collectors.toList());
    }
}
