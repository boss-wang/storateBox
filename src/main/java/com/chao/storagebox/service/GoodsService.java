package com.chao.storagebox.service;

import com.chao.storagebox.atom.GoodsAtom;
import com.chao.storagebox.entity.Goods;
import com.chao.storagebox.util.ToolsUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsService
{
    @Resource
    private GoodsAtom goodsAtom;

    public List<Goods> getGoodsList(String areaId, String boxId, String goodsName) {
        return goodsAtom.getGoodsList(areaId, boxId, goodsName);
    }

    public Goods getGoodsById(String goodsId)
    {
        return goodsAtom.getGoodsById(goodsId);
    }

    public void deleteGoods(String goodsId)
    {
        goodsAtom.deleteGoods(goodsId);
    }

    public void addGoods(Goods goods)
    {
        goods.setId(ToolsUtil.generateUUID());

        goodsAtom.addGoods(goods);
    }

    public void updateGoods(Goods goods)
    {
        goodsAtom.updateGoods(goods);
    }
}
