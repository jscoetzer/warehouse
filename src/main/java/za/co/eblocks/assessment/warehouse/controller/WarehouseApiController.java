package za.co.eblocks.assessment.warehouse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class WarehouseApiController {
    private static final Logger logger = LoggerFactory.getLogger(ReactiveController.class);

    @GetMapping(path = "/api/product", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Produc chatEvent(@RequestParam final String uuid){
        logger.info("Fluxing " + uuid);

        return sessionRepository.findById(uuid);
    }
}



public class ReactiveController {




    @GetMapping(path = "/session", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Session> chatEvent(@RequestParam final String uuid){
        logger.info("Fluxing " + uuid);

        return sessionRepository.findById(uuid);
    }

    @GetMapping(path = "/sow")
    public void addMesssage(
            @RequestParam final String id,
            @RequestParam final int index
    ){
        logger.info("Creating new message");
        Session session = sessionRepository.findOrCreateById(id);
        session.getMancala().sow(index);
    }
}
