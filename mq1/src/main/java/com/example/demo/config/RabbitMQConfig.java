package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.mq.callback.MessageSendConfirmCallback;
import com.example.demo.mq.callback.MessageSendReturnCallback;

/**
 * RabbitMQ配置
 * @author lizuodu
 * @date   2018年10月27日
 */
@Configuration
public class RabbitMQConfig {

	@Value("${spring.rabbitmq.addresses}")
	String address;// MQ地址
	@Value("${spring.rabbitmq.virtual-host}")
	String virtualHost;// MQ VirtualHost
	@Value("${spring.rabbitmq.username}")
	String username;// MQ登录名
	@Value("${spring.rabbitmq.password}")
	String password;// MQ登录密码

	public static final String EXCHANGE_NAME = "my.exchange";
	public static final String QUEUE_NAME1 = "order.queue";
	public static final String QUEUE_NAME2 = "my.queue";
	public static final String ROUTING_KEY_1 = "order:new";

	@Bean
	public DirectExchange directExchange() {
		DirectExchange directExchange = new DirectExchange(EXCHANGE_NAME, true, false);
		return directExchange;
	}

	@Bean
	public Queue firstQueue() {
		return new Queue(QUEUE_NAME1, true, false, false);
	}

	@Bean
	public Queue secondQueue() {
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("x-message-ttl", 60000);// 60秒自动删除
		return new Queue(QUEUE_NAME2, true, false, true, arguments);
	}

	@Bean
	public Binding bindingOne() {
		return BindingBuilder.bind(firstQueue()).to(directExchange()).with(ROUTING_KEY_1);
	}

	/**
	 * 定义rabbit template用于数据的接收和发送 可以设置消息确认机制和回调
	 * 
	 * @return
	 */
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(new SimpleMessageConverter()); // 默认使用的JDK的，所以消息对象需要实现Serializable
		// template.setMessageConverter(new Jackson2JsonMessageConverter());
		// //可以自定义消息转换器

		/**
		 * 1.如果消息没有到exchange,则confirm回调,ack=false 2.如果消息到达exchange,则confirm回调,ack=true
		 * 3.exchange到queue成功,则不回调return
		 * 4.exchange到queue失败,则回调return(需设置mandatory=true,否则不回调,消息就丢了)
		 */
		template.setConfirmCallback(messageSendConfirmCallback());
		/**
		 * 使用return-callback时必须设置mandatory为true，或者在配置中设置mandatory-expression的值为true，
		 * 可针对每次请求的消息去确定’mandatory’的boolean值， 只能在提供’return -callback’时使用，与mandatory互斥
		 */
		template.setReturnCallback(messageSendReturnCallback());
		
		template.setMandatory(true);
		return template;
	}
	
	@Bean
	public MessageSendConfirmCallback messageSendConfirmCallback() {
		return new MessageSendConfirmCallback();
	}
	
	@Bean
	public MessageSendReturnCallback messageSendReturnCallback() {
		return new MessageSendReturnCallback();
	}

}
