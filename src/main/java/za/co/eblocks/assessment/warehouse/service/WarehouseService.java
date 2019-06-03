package za.co.eblocks.assessment.warehouse.service;

import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import za.co.eblocks.assessment.warehouse.events.ProductCreatedEvent;
import za.co.eblocks.assessment.warehouse.model.Product;
import za.co.eblocks.assessment.warehouse.repository.WarehouseReactiveRepository;

@Log4j2
@Service
public class WarehouseService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private WarehouseReactiveRepository warehouseReactiveRepository;


    public Flux<Product> findAll() {
        return this.warehouseReactiveRepository.findAll();
    }

    public Flux<Product> findByName(String name) {
        return this.warehouseReactiveRepository.findByName(name);
    }

    public Mono<Product> get(String id) {
        return this.warehouseReactiveRepository.findById(id);
    }

    public Flux<Product> findAllByCategoryNameAndSupplierName(String categoryName, String supplierName){
        return warehouseReactiveRepository.findAllByCategoryNameAndSupplierName(categoryName, supplierName);
    }

    public Flux<Product> findAllBySupplierName(String supplierName){
        return warehouseReactiveRepository.findAllBySupplierName(supplierName);
    }

    public Flux<Product> findAllByCategoryName(String categoryName){
        return warehouseReactiveRepository.findAllByCategoryName(categoryName);
    }

    public Mono<Void> deleteById(String id) {
        return this.warehouseReactiveRepository.deleteById(id);
    }

    public Mono<Product> delete(String id) {
        return this.warehouseReactiveRepository
            .findById(id)
            .flatMap(p -> this.warehouseReactiveRepository.deleteById(p.getId()).thenReturn(p))
                .doOnSuccess(p -> this.publisher.publishEvent(new ProductCreatedEvent(p)));
    }



    public Mono<Product> create(Product product) {
        return this.warehouseReactiveRepository
                .save(product)
                .doOnSuccess(p -> this.publisher.publishEvent(new ProductCreatedEvent(p)));
    }
}