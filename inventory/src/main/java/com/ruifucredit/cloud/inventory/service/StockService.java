package com.ruifucredit.cloud.inventory.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruifucredit.cloud.inventory.repository.db.StockRepository;
import com.ruifucredit.cloud.inventory.pojo.po.Stock;
import com.ruifucredit.cloud.kit.po.PoKit;

import lombok.NonNull;

@Service
public class StockService implements IStockService {

    @Autowired
    private StockRepository stockRepo;

    @Override
    public com.ruifucredit.cloud.inventory.pojo.dto.Stock queryOne(Long id) {

        Stock result = stockRepo.findOne(id);

        if (result != null) return result.build();

        return new com.ruifucredit.cloud.inventory.pojo.dto.Stock();
    }

    @Override
    public com.ruifucredit.cloud.inventory.pojo.dto.Stock queryBySubGoodsId(Long id, Long subId) {

        Stock result = stockRepo.findByGoodsIdAndSubGoodsId(id, subId);

        if (result != null) return result.build();

        return new com.ruifucredit.cloud.inventory.pojo.dto.Stock();
    }

    @Override
    public List<com.ruifucredit.cloud.inventory.pojo.dto.Stock> queryByGoodsId(Long id) {

        List<Stock> stocks = stockRepo.findByGoodsId(id);

        return stocks.stream().map(stock -> stock.build()).collect(Collectors.toList());

    }

    @Override
    public com.ruifucredit.cloud.inventory.pojo.dto.Stock modify(@NonNull com.ruifucredit.cloud.inventory.pojo.dto.Stock param, @NonNull Long id) {

        Stock to = stockRepo.findOne(id);

        // 最后更新时间使用当前服务器时间，库存和状态使用入参
        to.setUpdateTime(new Date()).setStockNumber(param.getStockNumber()).setStockStatus(param.getStockStatus());

        return stockRepo.save(to).build();

    }

    @Override
    public List<com.ruifucredit.cloud.inventory.pojo.dto.Stock> create(@NonNull List<com.ruifucredit.cloud.inventory.pojo.dto.Stock> params) {

        String initStatus = com.ruifucredit.cloud.inventory.pojo.dto.Stock.YES_STATUS;
        Date now = new Date();

        List<Stock> stocks = params.stream().map(stock -> new Stock(stock).setStockId(null).setCreateTime(now).setUpdateTime(now).setStockStatus(initStatus)).collect(Collectors.toList());

        stocks = stockRepo.save(stocks);

        return stocks.stream().map(stock -> stock.build()).collect(Collectors.toList());

    }

    @Override
    public com.ruifucredit.cloud.inventory.pojo.dto.Stock create(@NonNull com.ruifucredit.cloud.inventory.pojo.dto.Stock stock) {

        List<com.ruifucredit.cloud.inventory.pojo.dto.Stock> results = create(Collections.singletonList(stock));

        if (results.size() == 1) results.get(0);

        return null;
    }

    @Override
    public com.ruifucredit.cloud.inventory.pojo.dto.Stock remove(@NonNull Long id) {

        Stock stock = stockRepo.findOne(id);

        if (stock == null) return new com.ruifucredit.cloud.inventory.pojo.dto.Stock();

        stockRepo.delete(stock.getStockId());

        return stock.build();

    }

}
