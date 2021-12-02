package com.amsidh.mvc.controller;

import com.amsidh.mvc.Domain;
import com.amsidh.mvc.DomainList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/domain")
@Slf4j
public class DomainController {

    private final KafkaTemplate<String, Domain> kafkaTemplate;
    private final static String KAFKA_TOPIC_NAME = "web-domains";

    @GetMapping("/lookup/{name}")
    public String getAllDomainsByName(@PathVariable("name") String name) {
        Mono<DomainList> domainListMono = WebClient.builder().build().get().uri("https://api.domainsdb.info/v1/domains/search?domain=" + name)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(DomainList.class);
        domainListMono.subscribe(domainList -> domainList.getDomains().forEach(domain -> {
            log.info("Domain {}"+ domain);
            kafkaTemplate.send(KAFKA_TOPIC_NAME, domain);
        }));
        return "Request for filtering domain is submitted!!!";
    }

}
