package ru.coderiders.generator.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@EnableRabbit
@Configuration
public class RabbitConfig {
    @Value("${rabbit.titles.exchange:snapshot-exchange}")
    private String snapshotExchange;
    @Value("${rabbit.host-name}")
    private String rabbitHostName;
    @Value("${rabbit.titles.hive-queue:hive-snapshot}")
    private String hiveQueueName;
    @Value("${rabbit.titles.family-queue:family-snapshot}")
    private String familyQueueName;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitHostName);
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin(List<Queue> queues) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
        queues.forEach(rabbitAdmin::declareQueue);
        return rabbitAdmin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        rabbitTemplate.setExchange(snapshotExchange);
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue hiveSnapshotQueue() {
        return new Queue(hiveQueueName);
    }

    @Bean
    public Queue familySnapshotQueue() {
        return new Queue(familyQueueName);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(snapshotExchange);
    }

    @Bean
    public Binding hiveSnapshotBinding() {
        return BindingBuilder.bind(hiveSnapshotQueue()).to(directExchange()).with(hiveQueueName);
    }

    @Bean
    public Binding familySnapshotBinding() {
        return BindingBuilder.bind(familySnapshotQueue()).to(directExchange()).with(familyQueueName);
    }
}
