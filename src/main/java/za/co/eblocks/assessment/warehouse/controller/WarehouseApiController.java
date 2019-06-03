package za.co.eblocks.assessment.warehouse.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
//import org.springframework.web.servlet.ModelAndView;

import reactor.core.publisher.Mono;
import za.co.eblocks.assessment.warehouse.model.*;
import za.co.eblocks.assessment.warehouse.repository.WarehouseReactiveRepository;
import za.co.eblocks.assessment.warehouse.service.WarehouseService;

import java.security.PublicKey;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
public class WarehouseApiController {

    private WarehouseService warehouseService;

    WarehouseApiController(WarehouseService warehouseService){
        this.warehouseService = warehouseService;
    }

    @PostMapping(path = "/product")
    public @ResponseBody
    Mono<Product> addProduct(@RequestBody Product product){
        return warehouseService.create(product);
    }

    @GetMapping(path="/products")
    public @ResponseBody Flux<Product> getProductsByCategoryAndSupplier(){
        log.info("Finding all products");
        return warehouseService.findAll();
    }

    @GetMapping(path="/products", params = {"category"})
    public @ResponseBody Flux<Product> getProductsByCategoryAndSupplier(
            @RequestParam final String category){
        log.info("Finding products for category " + category);
        return warehouseService.findAllByCategoryName(category);
    }

    @GetMapping(path="/products", params = {"supplier"})
    public @ResponseBody Flux<Product> getProductsBySupplier(
            @RequestParam final String supplier){
        log.info("Finding products for supplier " + supplier);
        return warehouseService.findAllBySupplierName(supplier);
    }

    @GetMapping(path="/products", params = {"category", "supplier"})
    public @ResponseBody Flux<Product> getProductsByCategoryAndSupplier(
            @RequestParam final String category,
            @RequestParam final String supplier){
        log.info("Finding products for category " + category + " and supplier " + supplier);
        return warehouseService.findAllByCategoryNameAndSupplierName(category, supplier);
    }


    @GetMapping("/index")
    public ModelAndView index() {

        ModelAndView model = new ModelAndView();
        model.addObject("products", warehouseService.findAll());
        model.setViewName("index");
        return model;
    }
}
