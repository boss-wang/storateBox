package com.chao.storagebox.controller;

import com.chao.storagebox.entity.Goods;
import com.chao.storagebox.service.GoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class GoodsController
{
    @Resource
    private GoodsService goodsService;


    @GetMapping("/goodsList")
    public List<Goods> getGoodsList(String areaId, String boxId, String goodsName) {
        return goodsService.getGoodsList(areaId, boxId, goodsName);
    }

    @GetMapping("/goods")
    public Goods getGoodsById(String goodsId)
    {
        return goodsService.getGoodsById(goodsId);
    }

    @PutMapping("/goods")
    public void updateGoods(Goods goods)
    {
        goodsService.updateGoods(goods);
    }

    @DeleteMapping("/goods")
    void deleteGoods(String goodsId)
    {
        goodsService.deleteGoods(goodsId);
    }

    @PostMapping("/goods")
    void addGoods(Goods goods)
    {
        goodsService.addGoods(goods);
    }

}
