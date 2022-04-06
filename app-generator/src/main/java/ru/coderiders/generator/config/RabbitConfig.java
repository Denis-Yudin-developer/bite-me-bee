package ru.coderiders.generator.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitConfig {
    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        rabbitTemplate.setExchange("snapshot-exchange");
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue hiveSnapshotQueue() {
        return new Queue("hive-snapshot");
    }

    @Bean
    public Queue familySnapshotQueue() {
        return new Queue("family-snapshot");
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("snapshot-exchange");
    }

    @Bean
    public Binding hiveSnapshotBinding() {
        return BindingBuilder.bind(hiveSnapshotQueue()).to(directExchange()).with("hive-snapshot");
    }

    @Bean
    public Binding familySnapshotBinding() {
        return BindingBuilder.bind(familySnapshotQueue()).to(directExchange()).with("family-snapshot");
    }
}
