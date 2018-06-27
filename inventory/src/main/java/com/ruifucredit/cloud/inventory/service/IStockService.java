package com.ruifucredit.cloud.inventory.service;

import java.util.List;

import com.ruifucredit.cloud.inventory.pojo.dto.Stock;

public interface IStockService {

	Stock queryOne(Long id);

	Stock queryBySubGoodsId(Long id, Long subId);

	List<Stock> queryByGoodsId(Long id);

	Stock modify(Stock stock, Long id);

	Stock create(Stock stock);

	Stock remove(Long id);

	List<Stock> create(List<Stock> params);

}
