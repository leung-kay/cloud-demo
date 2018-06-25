package com.ruifucredit.cloud.commodity.pojo.po;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "c_sub_goods")
public class SubGoods {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sub_goods_id_generator")
    @SequenceGenerator(name = "sub_goods_id_generator", sequenceName = "c_sub_goods_seq", allocationSize = 1)
    private Long subGoodsId;
    private String subName;
    private BigDecimal goodsPrice;
    private Date createTime;
    private Date updateTime;
    private String subStatus;
    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    public SubGoods() {
        super();
    }

    public SubGoods(com.ruifucredit.cloud.commodity.pojo.dto.SubGoods subGoods) {
        this();
        BeanUtils.copyProperties(subGoods, this);
    }

    public com.ruifucredit.cloud.commodity.pojo.dto.SubGoods build() {

        com.ruifucredit.cloud.commodity.pojo.dto.SubGoods subGoods =
                new com.ruifucredit.cloud.commodity.pojo.dto.SubGoods();

        BeanUtils.copyProperties(this, subGoods);

        subGoods.setGoodsId(this.getGoods().getGoodsId());

        return subGoods;
    }

}
