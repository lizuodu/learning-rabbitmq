package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Order;
import com.example.demo.service.OrderService;

/**
 * 订单
 * @author lizuodu
 * @date   2018年10月27日
 */
@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired private OrderService orderService;
	
	@RequestMapping("/find/{id}")
	public Order getOrder(@PathVariable("id") Long id) {
		return this.orderService.findByPrimaryKey(id);
	}
	
	/**
	 * 创建一个订单
	 */
	@RequestMapping("/write")
	public void writeOrder() {
		this.orderService.writeOrder();
	}

}
