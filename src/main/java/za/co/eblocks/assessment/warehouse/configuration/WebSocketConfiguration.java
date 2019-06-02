package za.co.eblocks.assessment.warehouse.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Flux;
import za.co.eblocks.assessment.warehouse.events.ProductCreatedEvent;
import za.co.eblocks.assessment.warehouse.events.ProductCreatedEventPublisher;

import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Log4j2
@Configuration
public class WebSocketConfiguration {

    @Bean
    public Executor executor() {
        return Executors.newSingleThreadExecutor();
    }

    @Bean
    public HandlerMapping handlerMapping(WebSocketHandler wsh) {
        return new SimpleUrlHandlerMapping() {
            {
                log.info("Creating websocket handler");
                setUrlMap(Collections.singletonMap("/ws/products", wsh));
                setOrder(10);
            }
        };
    }
    @Bean
    public WebSocketHandlerAdapter webSocketHandlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

    @Bean
    public WebSocketHandler webSocketHandler(
            ObjectMapper objectMapper,
            ProductCreatedEventPublisher eventPublisher
    ) {

        Flux<ProductCreatedEvent> publish = Flux
            .create(eventPublisher)
            .share();

        return session -> {
            Flux<WebSocketMessage> messageFlux = publish
                .map(evt -> {
                    try {
                        return objectMapper.writeValueAsString(evt.getSource());
                    }
                    catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(str -> {
                    log.info("sending " + str);
                    return session.textMessage(str);
                });

            return session.send(messageFlux);
        };
    }

}