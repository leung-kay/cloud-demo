package com.ruifucredit.cloud.upfront.repository.http;

import java.net.URI;
import java.nio.charset.StandardCharsets;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.ruifucredit.cloud.upfront.pojo.dto.Goods;
import com.ruifucredit.cloud.kit.dto.Outcoming;

import lombok.SneakyThrows;

@Repository
public class GoodsClient {

    private final Logger logger = LoggerFactory.getLogger(GoodsClient.class);

    @Autowired
    private RestTemplate rest;

    @HystrixCommand(fallbackMethod = "queryGoodsForFail")
    @SneakyThrows
    public Outcoming<Goods> queryGoods(String url) {

        RequestEntity<Void> request = RequestEntity.get(new URI(url)).accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8).build();

        ResponseEntity<Outcoming<Goods>> responseFromGoods = rest.exchange(request,
                new ParameterizedTypeReference<Outcoming<Goods>>() {
                });

        if (responseFromGoods.getStatusCode() == HttpStatus.OK) {
            return responseFromGoods.getBody();
        } else {
            throw new RuntimeException(responseFromGoods.toString());
        }

    }

    public Outcoming<Goods> queryGoodsForFail(String url) {

        logger.info("GoodsClient.queryGoodsForFail.url: {}", url);

        return new Outcoming<>(Outcoming.FAIL_CODE, null);
    }

}
