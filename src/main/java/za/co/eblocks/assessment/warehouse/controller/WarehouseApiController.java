package za.co.eblocks.assessment.warehouse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;

import za.co.eblocks.assessment.warehouse.model.*;

@RestController
public class WarehouseApiController {
    private static final Logger logger = LoggerFactory.getLogger(WarehouseApiController.class);

    @GetMapping(path = "/api/suppliers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Supplier> getAllSuppliers(){
        logger.info("Retrieving suppliers");

        return null;
    }

    @GetMapping(path = "/api/categories", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Category> getAllCategories(){
        logger.info("Retrieving categories");

        return null;
    }

    @GetMapping(path = "/api/products", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> findProductsBySupplierAndCategory(
            @RequestParam final Category category,
            @RequestParam final Product product
    ){

        return null;
    }
}
