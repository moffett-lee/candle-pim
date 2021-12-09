package com.amber.insect.stocks.demo.rep;

import java.util.List;

import com.amber.insect.stocks.demo.entity.StocksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * <p>Description: </p>
 * @date  
 * @author   
 * @version 1.0
 * @name   
 * <p>Copyright:Copyright(c)2020</p>
 */
@RepositoryRestResource(path = "/stocks")
public interface StocksRepository  extends JpaRepository<StocksEntity, Long> {

    /**
     * 根据股票名称查找所对应的股票数据
     * @param list
     * @return
     */
    List<StocksEntity> findByNameInOrderById(@Param("list")List<String> list);

    /**
     * 根据名称查询股票信息
     * @param name
     * @return
     */
    public StocksEntity findByName(@Param("name")String name);
}
