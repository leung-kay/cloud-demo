package com.ruifucredit.cloud.inventory.controller;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.ruifucredit.cloud.kit.aop.Catch;
import com.ruifucredit.cloud.kit.aop.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ruifucredit.cloud.inventory.service.IStockService;
import com.ruifucredit.cloud.inventory.pojo.dto.Stock;
import com.ruifucredit.cloud.kit.dto.Outgoing;

import lombok.SneakyThrows;

@RestController
@Slf4j
@Log
@Catch
public class StockController {

    @Autowired
    private IStockService stockService;

    // http://localhost:9020/stock/1
    /**
     * 单独查询库存
     * @param id 库存编号
     * @return
     */
    @GetMapping("stock/{id}")
    public Outgoing<Stock> queryStock(@PathVariable(name = "id") Long id) {

        Stock result = stockService.queryOne(id);

        return new Outgoing<>(result);

    }

    // http://localhost:9020/stock/commodity/1
    /**
     * 查询商品库存
     * @param id 商品编号
     * @return
     */
    @GetMapping("stock/commodity/{id}")
    @SneakyThrows
    public Outgoing<List<Stock>> queryCommodityStock(@PathVariable(name = "id") Long id) {

        List<Stock> result = stockService.queryByGoodsId(id);

        // 随机休眠0-9s 方便引发前置服务的断路器
        int sleepSeconds = new Random(System.currentTimeMillis()).nextInt(10);

        log.info("StockController.queryCommodityStock.sleepSeconds: {}", sleepSeconds);

        TimeUnit.SECONDS.sleep(sleepSeconds);

        return new Outgoing<>(result);

    }

    // http://localhost:9020/stock/commodity/1/1
    /**
     * 查询商品库存
     * @param id 商品编号
     * @param subId 子商品编号
     * @return
     */
    @GetMapping("stock/commodity/{id}/{subId}")
    public Outgoing<Stock> queryCommodityStock(@PathVariable(name = "id") Long id, @PathVariable(name = "subId") Long subId) {

        Stock result = stockService.queryBySubGoodsId(id, subId);

        return new Outgoing<>(result);

    }

    // http://localhost:9020/stock/1
    // {"goodsId":999,"subGoodsId":888,"stockNumber":33,"stockStatus":"N"}
    /**
     * 更新库存
     * @param id 库存编号
     * @param stock 库存
     * @return
     */
    @PutMapping("stock/{stockId}")
    public Outgoing<Stock> modifyStock(@PathVariable(name = "stockId") Long id, @RequestBody Stock stock) {

        Stock result = stockService.modify(stock, id);

        return new Outgoing<>(result);

    }

    // http://localhost:9020/stock/
    // [{"goodsId":1,"subGoodsId":1,"stockNumber":20},{"goodsId":1,"subGoodsId":2,"stockNumber":35}]
    /**
     * 创建库存
     * @param stock 商品库存列表
     * @return
     */
    @PostMapping("stock")
    public Outgoing<List<Stock>> createStock(@RequestBody List<Stock> stock) {

        List<Stock> result = stockService.create(stock);

        return new Outgoing<>(result);
    }

    /**
     * 删除库存
     * @param id 库存编号
     * @return
     */
    @DeleteMapping("stock/{stockId}")
    public Outgoing<Stock> removeStock(@PathVariable(name = "stockId") Long id) {

        Stock result = stockService.remove(id);

        return new Outgoing<>(result);

    }

}
