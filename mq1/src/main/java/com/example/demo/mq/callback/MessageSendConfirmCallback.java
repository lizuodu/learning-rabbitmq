package com.example.demo.mq.callback;

import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * 消息发送交换机回调
 * @author lizuodu
 * @date   2018年10月27日
 */
public class MessageSendConfirmCallback implements ConfirmCallback {

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (ack) {
            System.out.println("消息发送到exchange成功");
            // TODO 删除 msgId 与 Message 的关系
        } else {
            System.err.println("消息发送到exchange失败");
            // TODO 消息发送到exchange失败 ， 重新发送
        }
	}

}
