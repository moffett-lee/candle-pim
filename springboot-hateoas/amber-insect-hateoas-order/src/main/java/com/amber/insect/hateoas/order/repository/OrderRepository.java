package com.amber.insect.hateoas.order.repository;

import com.amber.insect.hateoas.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * @ClassName OrderRepository
 * @Description TODO
 * @Author Amber.L
 * @Date 2021/12/9 20:41
 * @Version 1.0
 **/
@RepositoryRestResource(path = "/order")
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {


    /**
     * @Author amber.liyuze
     * @Description  据用户查找获取所有的订单信息
     * @Date 2021/12/9 20:47
     * @Param [java.lang.String]
     * @Return java.util.List<com.amber.insect.hateoas.order.entity.OrderEntity>
     */
    public List<OrderEntity> findAllByUser(@Param("user") String user);
}
