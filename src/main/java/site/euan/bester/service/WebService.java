package site.euan.bester.service;

import site.euan.bester.domain.vo.NavigationBarInfoVO;

import java.util.List;

public interface WebService {
    List<NavigationBarInfoVO> getHeadInfos();
}
