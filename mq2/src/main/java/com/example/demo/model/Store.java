package com.example.demo.model;

import java.io.Serializable;

/**
 * 库存
 * @author lizuodu
 * @date   2018年10月27日
 */
public class Store implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String productCode;
	private Integer sku;

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

	public Integer getSku() {
		return sku;
	}

	public void setSku(Integer sku) {
		this.sku = sku;
	}

}
