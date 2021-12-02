package com.amsidh.mvc.config.service;

import com.amsidh.mvc.Domain;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class DomainService {
    @Bean
    public Consumer<KStream<String, Domain>> serviceDomain() {
        return record -> record.foreach((key, value) -> {
            log.info("Domain saved with domain details {}", value.toString());
        });
    }
}
