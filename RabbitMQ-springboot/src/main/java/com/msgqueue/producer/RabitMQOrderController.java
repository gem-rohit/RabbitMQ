package com.msgqueue.producer;


import com.msgqueue.entity.Order;
import com.msgqueue.entity.OrderDTO;
import com.msgqueue.rabbitmqconfig.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RabitMQOrderController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/order")
    OrderDTO placeOrder(@RequestBody Order order)
    {
        System.out.println("inside placeorder ");
        OrderDTO orderDTO = new OrderDTO(order ,"Order placed","Hi producer,Order placed");
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE,RabbitMQConfig.ROUTING_KEY,orderDTO);
        return  orderDTO;
    }

}
