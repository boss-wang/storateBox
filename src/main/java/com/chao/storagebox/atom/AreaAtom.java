package com.chao.storagebox.atom;


import com.chao.storagebox.dao.BoxMapper;
import com.chao.storagebox.dao.GoodsMapper;
import com.chao.storagebox.entity.Box;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import com.chao.storagebox.entity.Area;
import com.chao.storagebox.dao.AreaMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AreaAtom
{
    @Resource
    private AreaMapper areaMapper;

    @Resource
    private BoxMapper boxMapper;

    @Resource
    private GoodsMapper goodsMapper;

    public List<Area> getAreaList()
    {
        return areaMapper.getAreaList();
    }

    public Area getAreaById(String areaId)
    {
        return areaMapper.getAreaById(areaId);
    }

    public boolean addArea(Area area)
    {
        return areaMapper.addArea(area) > 0;
    }

    public boolean updateArea(Area area)
    {
        return areaMapper.updateArea(area) > 0;
    }

    public boolean deleteArea(String areaId)
    {
        List<Box> boxList = boxMapper.getBoxList(areaId);

        boxList.forEach(box -> goodsMapper.deleteGoodsByBoxId(box.getId()));

        boxMapper.deleteBoxByAreaId(areaId);

        areaMapper.deleteArea(areaId);

        return true;
    }
}