package com.ruifucredit.cloud.upfront.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruifucredit.cloud.upfront.pojo.dto.Goods;
import com.ruifucredit.cloud.upfront.pojo.dto.Stock;
import com.ruifucredit.cloud.kit.dto.Outcoming;
import com.ruifucredit.cloud.upfront.pojo.dto.Commodity;
import com.ruifucredit.cloud.upfront.pojo.dto.SubCommodity;
import com.ruifucredit.cloud.upfront.repository.http.GoodsClient;
import com.ruifucredit.cloud.upfront.repository.http.StockClient;

import lombok.NonNull;
import lombok.SneakyThrows;

@Service
public class CommodityService implements ICommodityService {

    @Autowired
    private GoodsClient goodsClient;
    @Autowired
    private StockClient stockClient;

    @SneakyThrows
    @Override
    public Commodity query(@NonNull Long goodsId) {

        // commodity主要由goods组成
        Commodity result = new Commodity();

        // 使用LoadBalanced从商品服务集群中获取商品信息
        Outcoming<Goods> goodsResult = goodsClient.queryGoods("http://COMMODITY/goods/" + goodsId);

        if (goodsResult.isSuccess()) {

            // 使用LoadBalanced从库存服务集群中获取库存信息
            Outcoming<List<Stock>> stocksResult = stockClient.queryStocks("http://INVENTORY/stock/commodity/" + goodsId);

            if (stocksResult.isSuccess()) {

                // 子产品和库存记录条数一致才认为是正确
                if(goodsResult.getResult().getSubGoodses().size()==stocksResult.getResult().size()) {

                    // 在这里复制属性 避免返回不完整的数据
                    BeanUtils.copyProperties(goodsResult.getResult(), result, "subGoodses");

                    // subcommodities由subGoods和stocks组成 这里是stream方式实现的双重for循环加条件判断
                    List<SubCommodity> subCommodities = goodsResult.getResult().getSubGoodses().stream().flatMap(subGoods ->
                            stocksResult.getResult().stream().filter(stock -> stock.getSubGoodsId() == subGoods.getSubGoodsId()).map(stock -> {
                                SubCommodity subCommodity = new SubCommodity();
                                BeanUtils.copyProperties(stock, subCommodity, "createTime", "updateTime");
                                BeanUtils.copyProperties(subGoods, subCommodity);
                                return subCommodity;
                            })).collect(Collectors.toList());

                    result.setSubCommodities(subCommodities);

                }

            }
        }

        return result;

    }


}
