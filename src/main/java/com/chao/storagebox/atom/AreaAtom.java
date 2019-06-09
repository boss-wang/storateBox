package com.chao.storagebox.atom;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import com.chao.storagebox.entity.Area;
import com.chao.storagebox.dao.AreaMapper;

import java.util.List;

@Service
@Transactional
public class AreaAtom
{
    @Resource
    private AreaMapper areaMapper;

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
        return areaMapper.deleteArea(areaId) > 0;
    }
}