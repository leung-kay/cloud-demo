package com.ruifucredit.cloud.commodity.pojo.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "c_goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goods_id_generator")
    @SequenceGenerator(name = "goods_id_generator", sequenceName = "c_goods_seq", allocationSize = 1)
    private Long goodsId;
    private String goodsName;
    private String goodsType;
    private Date createTime;
    private Date updateTime;
    private String goodsStatus;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "goods_id")
    private List<SubGoods> subGoodses;

    public Goods() {
        super();
    }

    public Goods(com.ruifucredit.cloud.commodity.pojo.dto.Goods goods) {
        this();

        BeanUtils.copyProperties(goods, this, "subGoodses");

        if (goods.getSubGoodses() != null) {
            subGoodses = new ArrayList<>(goods.getSubGoodses().size());
            for (com.ruifucredit.cloud.commodity.pojo.dto.SubGoods subGoods : goods.getSubGoodses()) {
                this.subGoodses.add(new SubGoods(subGoods).setGoods(this));
            }
        }
    }

    public com.ruifucredit.cloud.commodity.pojo.dto.Goods build() {
        com.ruifucredit.cloud.commodity.pojo.dto.Goods goods =
                new com.ruifucredit.cloud.commodity.pojo.dto.Goods();

        BeanUtils.copyProperties(this, goods, "subGoodses");

        if (getSubGoodses() != null) {

            List<com.ruifucredit.cloud.commodity.pojo.dto.SubGoods> subGoodses =
                    new ArrayList<>(getSubGoodses().size());

            for (SubGoods subGoods : getSubGoodses()) {
                subGoodses.add(subGoods.build());
            }

            goods.setSubGoodses(subGoodses);
        }

        return goods;
    }

}
