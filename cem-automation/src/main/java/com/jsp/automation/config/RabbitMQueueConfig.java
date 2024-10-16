package com.jsp.automation.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQueueConfig {
	public static final String QUEUE_NAME = "myQueue";
	public static final String EXCHANGE_NAME = "myExchange";
	public static final String ROUTING_KEY = "myRoutingKey";

//	public RabbitMQueueConfig() {
//		System.out.println(this.getClass().getSimpleName());
//	}

	@Bean
	public Queue getQueue() {
		return new Queue(QUEUE_NAME, false);

	}

	@Bean
	public TopicExchange getExchange() {
		return new TopicExchange(EXCHANGE_NAME);
	}

	@Bean
	public Binding getBinding(Queue queue, TopicExchange topicExchange) {
		return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
	}

	@Bean
	public ConnectionFactory getConnectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		return connectionFactory;
	}

	@Bean
	public RabbitTemplate getRabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(jackson2JsonMessageConverter());

		return template;
	}

	@Bean
	public RabbitListenerContainerFactory<SimpleMessageListenerContainer> rabbitListenerContainerFactory(
			ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setMessageConverter(jackson2JsonMessageConverter());
		return factory;
	}

	@Bean
	public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
		Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
		DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
		typeMapper.addTrustedPackages("com.jsp.automation.dto"); // Add your package here
		typeMapper.addTrustedPackages("com.jsp.automation.entity"); // Add your package here
		converter.setJavaTypeMapper(typeMapper);

		return converter;
	}

}
