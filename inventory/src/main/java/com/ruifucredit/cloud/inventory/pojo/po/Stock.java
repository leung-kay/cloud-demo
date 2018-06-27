package com.ruifucredit.cloud.inventory.pojo.po;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@Entity
@Table(name="i_stock")
public class Stock {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="stock_id_generator")
	@SequenceGenerator(name="stock_id_generator", sequenceName="i_stock_seq", allocationSize=1)
	private Long stockId;
	private Long goodsId;
	private Long subGoodsId;
	private BigDecimal stockNumber;
	private Date createTime;
	private Date updateTime;
	private String stockStatus;
	
	public Stock() {
		super();
	}
	
	public Stock(com.ruifucredit.cloud.inventory.pojo.dto.Stock stock) {
		this();
		BeanUtils.copyProperties(stock, this);
	}
	
	public com.ruifucredit.cloud.inventory.pojo.dto.Stock build() {
		com.ruifucredit.cloud.inventory.pojo.dto.Stock stock =
				new com.ruifucredit.cloud.inventory.pojo.dto.Stock();
		BeanUtils.copyProperties(this, stock);
		return stock;
	}
	
}
