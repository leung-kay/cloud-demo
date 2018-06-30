package com.ruifucredit.cloud.commodity.controller;

import java.util.List;

import com.ruifucredit.cloud.commodity.pojo.dto.Goods;
import com.ruifucredit.cloud.kit.aop.Catch;
import com.ruifucredit.cloud.kit.aop.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ruifucredit.cloud.commodity.service.IGoodsService;
import com.ruifucredit.cloud.kit.dto.Outgoing;

import lombok.SneakyThrows;

@RestController
@Log
@Catch
public class GoodsController {
	
	@Autowired
	private IGoodsService goodsService;

	// http://localhost:9010/goods/1
	@GetMapping("goods/{id}")
	@SneakyThrows
	public Outgoing<Goods> queryGoods(@PathVariable(name="id") Long id) {

		Goods result = goodsService.query(id);

		return new Outgoing<>(result);
		
	}

	// http://localhost:9010/goods?goodsName=某种食品
	@GetMapping("goods")
	public Outgoing<List<Goods>> queryGoods(@RequestParam(name="goodsName") String name) {
		
		List<Goods> result = goodsService.query(name);

		return new Outgoing<>(result);

	}

	// http://localhost:9010/goods
	// Content-Type=application/json;charset=UTF-8
	// {"goodsName":"某种食品","goodsType":"F","goodsStatus":"A","subGoodses":[{"subName":"好吃点","goodsPrice":10.5,"subStatus":"A"},{"subName":"多吃点","goodsPrice":9.8,"subStatus":"A"}]}
	@PostMapping("goods")
	public Outgoing<Goods> createGoods(@RequestBody Goods param) {
		
		Goods result = goodsService.create(param);
		
		return new Outgoing<>(result);
	}

}
