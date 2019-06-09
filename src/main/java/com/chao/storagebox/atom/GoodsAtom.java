package com.chao.storagebox.atom;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import com.chao.storagebox.entity.Goods;
import com.chao.storagebox.dao.GoodsMapper;
import java.util.List;

@Service
@Transactional
public class GoodsAtom {
	@Resource
	private GoodsMapper goodsMapper;

	public List<Goods> getGoodsList(String areaId,String boxId,String goodsName) {
		return goodsMapper.getGoodsList(areaId, boxId, goodsName);
	}

	public Goods getGoodsById(String goodsId)
	{
		return goodsMapper.getGoodsById(goodsId);
	}

	public void deleteGoods(String goodsId)
	{
		goodsMapper.deleteGoods(goodsId);
	}

	public void addGoods(Goods goods)
	{
		goodsMapper.addGoods(goods);
	}

	public void updateGoods(Goods goods)
	{
		goodsMapper.updateGoods(goods);
	}
}