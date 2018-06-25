package com.ruifucredit.cloud.commodity.repository.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ruifucredit.cloud.commodity.pojo.po.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Long> {
	
	List<Goods> findByGoodsName(String name);
	
}
