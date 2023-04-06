package com.shl.demo.region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionDao regionDao;

    @Override
    public List<RegionVO> getRegionTree() {
        // 从数据库中查询所有行政区数据
        List<RegionDTO> regionList = regionDao.findAll();
        // 将所有省级行政区按照父级ID分组
        Map<Long, List<RegionDTO>> regionMap = regionList.stream()
                .filter(region -> region.getLevel() == 1)
                .collect(Collectors.groupingBy(RegionDTO::getParentId));
        // 将省级行政区组成树状结构
        List<RegionVO> provinceList = regionMap.get(0L).stream()
                .map(this::convertToRegionVO)
                .collect(Collectors.toList());
        provinceList.forEach(province -> setChildren(province, regionMap));
        return provinceList;
    }

    private RegionVO convertToRegionVO(RegionDTO regionDTO) {
        RegionVO regionVO = new RegionVO();
        regionVO.setId(regionDTO.getId());
        regionVO.setLabel(regionDTO.getName());
        return regionVO;
    }

    private void setChildren(RegionVO regionVO, Map<Long, List<RegionDTO>> regionMap) {
        List<RegionVO> children = regionMap.get(regionVO.getId()).stream()
                .map(this::convertToRegionVO)
                .collect(Collectors.toList());
        if (!children.isEmpty()) {
            regionVO.setChildren(children);
            children.forEach(child -> setChildren(child, regionMap));
        }
    }
}
