package site.euan.bester.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import site.euan.bester.domain.model.Plate;

@Mapper
public interface PlateMapper extends BaseMapper<Plate> {
}
