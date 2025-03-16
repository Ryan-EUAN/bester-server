package site.euan.bester.service;

import site.euan.bester.domain.vo.PlateVO;

import java.util.List;

public interface ModuleService {
    /**
     * 获取全部模块的信息
     * @return
     */
    List<PlateVO> getModule();
}
