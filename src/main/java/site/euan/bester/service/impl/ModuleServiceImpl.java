package site.euan.bester.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import site.euan.bester.domain.model.Plate;
import site.euan.bester.domain.model.PlateInfo;
import site.euan.bester.domain.model.Post;
import site.euan.bester.domain.vo.PlateInfoVO;
import site.euan.bester.domain.vo.PlateVO;
import site.euan.bester.mapper.PlateInfoMapper;
import site.euan.bester.mapper.PlateMapper;
import site.euan.bester.service.ModuleService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ModuleServiceImpl implements ModuleService {
    private final PlateMapper plateMapper;
    private final PlateInfoMapper plateInfoMapper;
    private final MongoTemplate mongoTemplate;

    @Override
    public List<PlateVO> getModule() {
        // 获取所有启用的板块
        List<Plate> plates = plateMapper.selectList(new QueryWrapper<Plate>().eq("enable", true));
        // 将板块信息转换为视图对象
        return plates.stream().map(plate -> {
            // 获取板块相关的板块信息
            List<PlateInfo> plateInfos = plateInfoMapper.selectList(new QueryWrapper<PlateInfo>().eq("plate_id", plate.getId()));
            // 将板块信息转换为视图对象
            List<PlateInfoVO> plateInfoVOS = plateInfos.stream().map(plateInfo -> {
                // 计算相关帖子的数量
                long postCount = mongoTemplate.count(new Query(Criteria.where("plateId").is(plateInfo.getId())), Post.class);
                // 构建板块信息视图对象
                return PlateInfoVO.builder()
                        .id(plateInfo.getId())
                        .name(plateInfo.getPlateName())
                        .icon(plateInfo.getPlateIcon())
                        .path(plateInfo.getPlatePath())
                        .count(0)
                        .posts(postCount)
                        .lastPost("1小时前")
                        .topics(0)
                        .build();
            }).collect(Collectors.toList());

            // 构建板块视图对象
            return PlateVO.builder()
                    .title(plate.getTitle())
                    .plateInfos(plateInfoVOS)
                    .build();
        }).collect(Collectors.toList());
    }
}
