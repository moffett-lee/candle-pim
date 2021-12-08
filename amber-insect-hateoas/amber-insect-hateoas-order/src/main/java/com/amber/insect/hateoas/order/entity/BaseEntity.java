package com.amber.insect.hateoas.order.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @ClassName BaseEntity
 * @Description TODO
 * @Author Amber.L
 * @Date 2021/12/8 22:00
 * @Version 1.0
 **/
@MappedSuperclass
@Data
public class BaseEntity {


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
