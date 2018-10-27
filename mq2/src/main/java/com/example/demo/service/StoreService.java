package com.example.demo.service;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.RabbitMQConfig;
import com.example.demo.mapper.StoreMapper;
import com.example.demo.model.Store;
import com.rabbitmq.client.Channel;

/**
 * 
 * @author lizuodu
 * @date   2018年10月27日
 */
@Service
public class StoreService {

	@Resource
	private StoreMapper storeMapper;

	/**
	 * 更新库存数量
	 * 
	 * @param order
	 * @return
	 */
	@RabbitListener(bindings = @QueueBinding(value = @Queue(value = RabbitMQConfig.QUEUE_NAME1), 
			exchange = @Exchange(value = RabbitMQConfig.EXCHANGE_NAME), key = RabbitMQConfig.ROUTING_KEY_1))
	public void handleMessage(Message message, Channel channel) {

		if (channel.isOpen()) {
			try {
				String recmessage = new String(message.getBody());
				JSONObject order = JSON.parseObject(recmessage);

				Store store = this.storeMapper.findByProductCode(order.getString("product_code"));
				store.setSku(store.getSku() - order.getInteger("quantity"));

				this.storeMapper.update(store);
				
				channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			} catch (Exception ex) {
				// 处理消息失败，将消息重新放回队列
				try {
					channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
