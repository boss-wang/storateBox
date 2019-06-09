package com.chao.storagebox.service;

import com.chao.storagebox.atom.AreaAtom;
import com.chao.storagebox.atom.BoxAtom;
import com.chao.storagebox.entity.Area;
import com.chao.storagebox.entity.Box;
import com.chao.storagebox.util.ToolsUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AreaService
{
    @Resource
    private AreaAtom areaAtom;

    @Resource
    private BoxAtom boxAtom;


    public List<Area> getAreaList() {
        return areaAtom.getAreaList();
    }

    public Area getAreaById(String areaId)
    {
        return areaAtom.getAreaById(areaId);
    }

    public boolean addArea(Area area)
    {
        area.setId(ToolsUtil.generateUUID());

        return areaAtom.addArea(area);
    }

    public boolean updateArea(Area area)
    {
        return areaAtom.updateArea(area);
    }

    public boolean deleteArea(String areaId)
    {
        return areaAtom.deleteArea(areaId);
    }
}
