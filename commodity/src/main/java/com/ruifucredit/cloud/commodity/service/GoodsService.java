package com.ruifucredit.cloud.commodity.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.ruifucredit.cloud.commodity.pojo.po.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruifucredit.cloud.commodity.repository.db.GoodsRepository;

@Service
public class GoodsService implements IGoodsService {

	@Autowired
	private GoodsRepository goodsRepo;

	@Override
	public com.ruifucredit.cloud.commodity.pojo.dto.Goods query(Long id) {
		Goods goods = goodsRepo.findOne(id);

		if (goods != null) {
			goods.getSubGoodses();
            return goods.build();
		}

		return new com.ruifucredit.cloud.commodity.pojo.dto.Goods();
	}

	@Override
	public List<com.ruifucredit.cloud.commodity.pojo.dto.Goods> query(String name) {
		
		List<Goods> goodses = goodsRepo.findByGoodsName(name);

        return goodses.stream().map(goods -> goods.build()).collect(Collectors.toList());

	}

	@Override
	public com.ruifucredit.cloud.commodity.pojo.dto.Goods create(com.ruifucredit.cloud.commodity.pojo.dto.Goods param) {

		Date now = new Date();

		Goods goods = new Goods(param);

		// 新增 防止更新goods
		goods.setGoodsId(null).setCreateTime(now).setUpdateTime(now);

		// 新增 防止更新sub_goods
		if (goods.getSubGoodses() != null) {
		    goods.getSubGoodses().forEach(subGoods -> subGoods.setSubGoodsId(null).setCreateTime(now).setUpdateTime(now));
		}

		Goods result = goodsRepo.save(goods);

		return result.build();
	}

}
