package com.chao.storagebox.service;

import com.chao.storagebox.atom.BoxAtom;
import com.chao.storagebox.entity.Box;
import com.chao.storagebox.util.ToolsUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BoxService
{
    @Resource
    private BoxAtom boxAtom;

    public List<Box> getBoxList(String areaId) {
        return boxAtom.getBoxList(areaId);
    }

    public Box getBoxById(String boxId)
    {
        return boxAtom.getBoxById(boxId);
    }

    public boolean addBox(Box box)
    {
        box.setId(ToolsUtil.generateUUID());

        return boxAtom.addBox(box);
    }

    public boolean updateBox(Box box)
    {
        return boxAtom.updateBox(box);
    }

    public boolean deleteBox(String boxId)
    {
        return boxAtom.deleteBox(boxId);
    }

}
