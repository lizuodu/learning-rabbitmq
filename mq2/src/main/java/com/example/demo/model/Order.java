package com.example.demo.model;

import java.io.Serializable;

/**
 * 订单
 * @author lizuodu
 * @date   2018年10月27日
 */
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String productCode;
	private Integer quantity;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
