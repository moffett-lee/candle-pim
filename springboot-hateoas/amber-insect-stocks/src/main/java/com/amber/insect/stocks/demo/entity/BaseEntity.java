package com.amber.insect.stocks.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

/**
 * @ClassName BaseEntity
 * @Description TODO
 * @Author Amber.L
 * @Date 2021/12/8 22:00
 * @Version 1.0
 **/
@MappedSuperclass
@Data
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建时间
     */
    @Column(updatable = false)
    @CreationTimestamp
    private Date createTime;

    /**
     *
     */
    @UpdateTimestamp
    private Date updateTime;
}
