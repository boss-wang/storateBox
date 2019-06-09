package com.chao.storagebox.controller;

import com.chao.storagebox.entity.Area;
import com.chao.storagebox.service.AreaService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class AreaController
{
    @Resource
    private AreaService areaService;

    @GetMapping("/areaList")
    public List<Area> getAreaList() {
        return areaService.getAreaList();
    }

    @PostMapping("/area")
    public boolean addArea(Area area)
    {
        return areaService.addArea(area);
    }

    @PutMapping("/area")
    public boolean updateArea(Area area)
    {
        return areaService.updateArea(area);
    }

    @DeleteMapping("/area")
    public boolean deleteArea(@RequestParam String areaId)
    {
        return areaService.deleteArea(areaId);
    }

    @GetMapping("/area")
    public Area getAreaById(String areaId)
    {
        return areaService.getAreaById(areaId);
    }
}
