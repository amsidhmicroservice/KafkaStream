package com.amsidh.mvc.config;

import com.amsidh.mvc.Domain;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfig {

    @Value("${spring.kafka.producer.bootstrap-servers:localhost:29092,localhost:39092}")
    private String bootstrapServerConfig;

    @Bean
    public DefaultKafkaProducerFactory<String, Domain> getKafkaProducerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServerConfig);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory(properties, new StringSerializer(), new JsonSerializer<Domain>());
    }

    @Bean
    public KafkaTemplate<String, Domain> getKafkaTemplate() {
        return new KafkaTemplate(getKafkaProducerFactory());
    }
}
