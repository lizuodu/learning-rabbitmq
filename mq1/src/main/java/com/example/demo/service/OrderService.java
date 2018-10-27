package com.example.demo.service;

import java.util.UUID;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.RabbitMQConfig;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.Order;

/**
 * 
 * @author lizuodu
 * @date   2018年10月27日
 */
@Service
public class OrderService {

	@Autowired
    private RabbitTemplate rabbitSendTemplate;

	@Autowired
	private OrderMapper orderMapper;

	/**
	 * 更新订单数量
	 * 
	 * @param order
	 * @return
	 */
	public int update(Order order) {
		return this.orderMapper.update(order);
	}

	/**
	 * 查找订单
	 * 
	 * @param id
	 * @return
	 */
	public Order findByPrimaryKey(Long id) {
		return this.orderMapper.findByPrimaryKey(id);
	}

	/**
	 * 下单
	 */
	public void writeOrder() {
		// 下单100
		Order order = new Order();
		order.setId(1L);
		order.setProductCode("cookie");
		order.setQuantity(100);

		this.orderMapper.update(order);
		
		// 通知减库存
		JSONObject obj = new JSONObject();
		obj.put("product_code", order.getProductCode());
		obj.put("quantity", order.getQuantity());
		
		String msgId = UUID.randomUUID().toString();
		Message message = MessageBuilder.withBody(obj.toJSONString().getBytes()).setMessageId(msgId)
				.setContentType(MessageProperties.CONTENT_TYPE_JSON).build();
		
		CorrelationData data = new CorrelationData(msgId);
		
		this.rabbitSendTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_1, message, data);
	}

}
