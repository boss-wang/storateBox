package com.chao.storagebox.controller;

import com.chao.storagebox.entity.Box;
import com.chao.storagebox.service.BoxService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BoxController
{
    @Resource
    private BoxService boxService;

    @GetMapping("/boxList")
    public List<Box> getBoxList(String areaId) {
        return boxService.getBoxList(areaId);
    }

    @GetMapping("/box")
    public Box getBoxById(String boxId)
    {
        return boxService.getBoxById(boxId);
    }

    @PostMapping("/box")
    public boolean addBox(Box box)
    {
        return boxService.addBox(box);
    }

    @DeleteMapping("/box")
    public boolean deleteBox(String boxId)
    {
        return boxService.deleteBox(boxId);
    }

    @PutMapping("/box")
    public boolean updateBox(Box box)
    {
        return boxService.updateBox(box);
    }


}
