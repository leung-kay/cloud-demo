package com.ruifucredit.cloud.upfront.controller;

import com.ruifucredit.cloud.kit.aop.Catch;
import com.ruifucredit.cloud.kit.aop.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ruifucredit.cloud.kit.dto.Outgoing;
import com.ruifucredit.cloud.upfront.pojo.dto.Commodity;
import com.ruifucredit.cloud.upfront.service.ICommodityService;

@RestController
@Log
@Catch
public class CommodityController {

	@Autowired
	private ICommodityService commodityService;

	// http://localhost:9000/commodity/1
	/**
	 * 商品库存查询
	 * @param goodsId 商品编号
	 * @return
	 */
	@GetMapping("commodity/{id}")
	public Outgoing<Commodity> query(@PathVariable(name = "id") Long goodsId) {
		Commodity result = commodityService.query(goodsId);
		return new Outgoing<>(result);
	}

}
