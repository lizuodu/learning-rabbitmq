package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.model.Order;

/**
 * 
 * @author lizuodu
 * @date   2018年10月27日
 */
@Mapper
public interface OrderMapper {
	
	/**
	 * 更新订单数量
	 * @param order
	 * @return
	 */
	public int update(Order order);
	
	/**
	 * 查找订单
	 * @param id
	 * @return
	 */
	public Order findByPrimaryKey(@Param("id") Long id);

}
