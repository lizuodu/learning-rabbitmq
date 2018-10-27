package com.example.demo.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置
 * @author lizuodu
 * @date   2018年10月27日
 */
@Configuration
public class RabbitMQConfig {
	
	@Value("${spring.rabbitmq.addresses}")
	String address;//MQ地址
	@Value("${spring.rabbitmq.virtual-host}")
    String virtualHost;//MQ VirtualHost
    @Value("${spring.rabbitmq.username}")
    String username;//MQ登录名
    @Value("${spring.rabbitmq.password}")
    String password;//MQ登录密码
    
    public static final String EXCHANGE_NAME = "my.exchange";
	public static final String QUEUE_NAME1 = "order.queue";
	public static final String QUEUE_NAME2 = "my.queue";
	public static final String ROUTING_KEY_1 = "order:new";
    
    @Bean
    public ConnectionFactory connectionFactory() {
    	CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setAddresses(address);
        factory.setVirtualHost(virtualHost);
        factory.setUsername(username);
        factory.setPassword(password);
        return factory;
    }
	

}

