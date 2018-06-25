package com.ruifucredit.cloud.commodity.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruifucredit.cloud.commodity.pojo.dto.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruifucredit.cloud.commodity.repository.db.GoodsRepository;

@Service
public class GoodsService implements IGoodsService {

	@Autowired
	private GoodsRepository goodsRepo;

	@Override
	public Goods query(Long id) {
		com.ruifucredit.cloud.commodity.pojo.po.Goods goods = goodsRepo.findOne(id);

		if (goods != null) {
			goods.getSubGoodses();
		}

		return goods.build();
	}

	@Override
	public List<Goods> query(String name) {
		
		List<com.ruifucredit.cloud.commodity.pojo.po.Goods> goodses = goodsRepo.findByGoodsName(name);
		
		List<Goods> result = new ArrayList<>(goodses.size());
		
		for(com.ruifucredit.cloud.commodity.pojo.po.Goods goods : goodses) {
			result.add(goods.build());
		}
		
		return result;

	}

	@Override
	public Goods create(Goods param) {

		Date now = new Date();

		com.ruifucredit.cloud.commodity.pojo.po.Goods goods = new com.ruifucredit.cloud.commodity.pojo.po.Goods(param);

		// 新增 防止更新goods
		goods.setGoodsId(null).setCreateTime(now).setUpdateTime(now);

		// 新增 防止更新sub_goods
		if (goods.getSubGoodses() != null) {
			for (com.ruifucredit.cloud.commodity.pojo.po.SubGoods subGoods : goods.getSubGoodses()) {
				subGoods.setSubGoodsId(null).setCreateTime(now).setUpdateTime(now);
			}
		}

		com.ruifucredit.cloud.commodity.pojo.po.Goods result = goodsRepo.save(goods);

		return result.build();
	}

}
