package com.amber.insect.stocks.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author amber.liyuze
 * @Description
 * @Date 2021/12/9 20:59
 */
@Entity
@Data
@Table(name = "T_STOCKS")
@NoArgsConstructor
@AllArgsConstructor
public class StocksEntity extends BaseEntity {

    /**
     * 股票名称
     */
    private String name;

    /**
     * 股票价格
     */
    private Double price;

}
