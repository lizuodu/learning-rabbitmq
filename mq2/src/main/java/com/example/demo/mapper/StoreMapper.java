package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.model.Store;

/**
 * 库存
 * @author lizuodu
 * @date   2018年10月27日
 */
@Mapper
public interface StoreMapper {
	
	/**
	 * 更新库存数量
	 * @param order
	 * @return
	 */
	public int update(Store store);
	
	/**
	 * 查找库存
	 * @param id
	 * @return
	 */
	public Store findByPrimaryKey(@Param("id") Long id);
	
	public Store findByProductCode(@Param("code") String productCode);

}
