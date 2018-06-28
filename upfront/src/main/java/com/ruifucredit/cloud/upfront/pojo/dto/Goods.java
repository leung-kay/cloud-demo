package com.ruifucredit.cloud.upfront.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class Goods {

	private Long goodsId;
	private String goodsName;
	private String goodsType;
	private Date createTime;
	private Date updateTime;
	private String goodsStatus;
	private List<SubGoods> subGoodses;

}
