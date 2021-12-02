package com.amsidh.mvc.config.processor;

import com.amsidh.mvc.Domain;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
@Slf4j
public class DomainProcessor {

    @Bean
    public Function<KStream<String, Domain>, KStream<String, Domain>> processDomain() {
        return record -> record.filter((key, value) -> {
            log.info("Domain {} is {}", value.getDomain(), value.isDead() ? "Inactive" : "Active");
            return !value.isDead();
        });
    }
}
