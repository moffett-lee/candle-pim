package com.amber.insect.hateoas.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;

import javax.persistence.Entity;

/**
 * @ClassName StocksEntity
 * @Description TODO
 * @Author Amber.L
 * @Date 2021/12/8 22:02
 * @Version 1.0
 **/
@Entity
@Data
@Table(name = "T_STOCKS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StocksEntity extends BaseEntity{

    /**
     * 股票名称
     */
    private String name;

    /**
     * 股票价格
     */
    private Double price;
}
