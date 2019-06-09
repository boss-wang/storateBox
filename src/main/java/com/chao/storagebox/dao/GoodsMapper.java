package com.chao.storagebox.dao;

import com.chao.storagebox.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper
{
    List<Goods> getGoodsList(@Param("areaId") String areaId, @Param("boxId") String boxId, @Param("goodsName")String goodsName);

    Goods getGoodsById(@Param("goodsId")String goodsId);

    void deleteGoods(@Param("goodsId") String goodsId);

    void deleteGoodsByBoxId(@Param("boxId") String boxId);

    void addGoods(Goods goods);

    void updateGoods(Goods goods);
}