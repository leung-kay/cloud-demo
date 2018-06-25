package com.ruifucredit.cloud.commodity.controller;

import java.util.List;

import com.ruifucredit.cloud.commodity.pojo.dto.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ruifucredit.cloud.commodity.service.IGoodsService;
import com.ruifucredit.cloud.kit.dto.Outcoming;

import lombok.SneakyThrows;

@RestController
public class GoodsController {
	
	private Logger logger = LoggerFactory.getLogger(GoodsController.class);
	
	@Autowired
	private IGoodsService goodsService;

	// http://localhost:9010/goods/1
	@GetMapping("goods/{id}")
	@SneakyThrows
	public Outcoming<Goods> queryGoods(@PathVariable(name="id") Long id) {

		logger.info("GoodsController.queryGoods.id: {}", id);
		
		Goods result = goodsService.query(id);
		
		logger.info("GoodsController.queryGoods.result: {}", result);
		
		return new Outcoming<Goods>().setCode(Outcoming.OK_CODE).setResult(result);
		
	}

	// http://localhost:9010/goods?goodsName=某种食品
	@GetMapping("goods")
	public Outcoming<List<Goods>> queryGoods(@RequestParam(name="goodsName") String name) {
		
		List<Goods> result = goodsService.query(name);
		
		return new Outcoming<>(result);
		
	}

	// http://localhost:9010/goods
	// Content-Type=application/json;charset=UTF-8
	// {"goodsName":"某种食品","goodsType":"F","goodsStatus":"A","subGoodses":[{"subName":"好吃点","goodsPrice":10.5,"subStatus":"A"},{"subName":"多吃点","goodsPrice":9.8,"subStatus":"A"}]}
	@PostMapping("goods")
	public Outcoming<Goods> createGoods(@RequestBody Goods param) {
		
		Goods result = goodsService.create(param);
		
		return new Outcoming<>(result);
	}

}
