package com.ruifucredit.cloud.commidity;

import com.ruifucredit.cloud.commodity.CommodityStart;
import com.ruifucredit.cloud.commodity.controller.GoodsController;
import com.ruifucredit.cloud.commodity.pojo.dto.Goods;
import com.ruifucredit.cloud.commodity.service.GoodsService;
import com.ruifucredit.cloud.commodity.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Collections;
import java.util.Date;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GoodsController.class)
@ContextConfiguration(classes = CommodityStart.class)
public class GoodsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IGoodsService goodsService;

    @Test
    public void testQueryGoods() throws Throwable {

        // mock服务层
        given(this.goodsService.query(1L)).willReturn(new Goods().setGoodsId(1L).setGoodsName("好吃点").setGoodsType("L").setCreateTime(new Date()).setGoodsStatus("A").setUpdateTime(new Date()).setSubGoodses(Collections.emptyList()));

        // URL必须以/开头
        MockHttpServletRequestBuilder builder = get("/goods/1").contentType(MediaType.APPLICATION_JSON);

        mvc.perform(builder)
                .andExpect(status().isOk())
                // 只校验指定的键值对 未给出的不校验
                .andExpect(content().json("{\"code\":2000,\"result\":{\"goodsId\":1,\"goodsName\":\"好吃点\",\"goodsType\":\"L\",\"goodsStatus\":\"A\",\"subGoodses\":[]},\"message\":\"请求成功\",\"success\":true}"))
                // 打印请求和返回的信息
                .andDo(MockMvcResultHandlers.print())
                // 返回结果字符串
                .andReturn().getResponse().getContentAsString();

    }

}
