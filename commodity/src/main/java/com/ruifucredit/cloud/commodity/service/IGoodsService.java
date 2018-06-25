package com.ruifucredit.cloud.commodity.service;

import com.ruifucredit.cloud.commodity.pojo.dto.Goods;

import java.util.List;

public interface IGoodsService {
	
	Goods query(Long id);

	Goods create(Goods goods);

	List<Goods> query(String name);
	
}
