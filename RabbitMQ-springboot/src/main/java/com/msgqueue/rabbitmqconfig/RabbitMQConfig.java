package com.msgqueue.rabbitmqconfig;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE="rabbit_mq_queue";
    public static final String EXCHANGE="rabbit_mq_exchange";
    public static final String ROUTING_KEY="rabbit_mq_r_key";

    @Bean
    public Queue queue()
    {
        return new Queue(QUEUE);
    }
    @Bean
    public DirectExchange directExchange()
    {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue,DirectExchange directExchange)
    {
        return BindingBuilder.bind(queue).to(directExchange).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate getTemplete(ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
