package za.co.eblocks.assessment.warehouse.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import za.co.eblocks.assessment.warehouse.events.ProductCreatedEvent;
import za.co.eblocks.assessment.warehouse.model.Category;
import za.co.eblocks.assessment.warehouse.model.Product;
import za.co.eblocks.assessment.warehouse.model.Supplier;
import za.co.eblocks.assessment.warehouse.repository.WarehouseAggregationRepositoryInterface;
import za.co.eblocks.assessment.warehouse.repository.WarehouseReactiveRepository;

import java.util.List;

@Log4j2
@Service
public class WarehouseService {

    private final ApplicationEventPublisher publisher;
    private final WarehouseReactiveRepository warehouseReactiveRepository;
    private final WarehouseAggregationRepositoryInterface warehouseAggregationRepositoryInterface;

    WarehouseService(ApplicationEventPublisher publisher, WarehouseReactiveRepository warehouseReactiveRepository, WarehouseAggregationRepositoryInterface warehouseAggregationRepositoryInterface) {
        this.publisher = publisher;
        this.warehouseReactiveRepository = warehouseReactiveRepository;
        this.warehouseAggregationRepositoryInterface = warehouseAggregationRepositoryInterface;
    }

    public Flux<Product> findAll() {
        return this.warehouseReactiveRepository.findAll();
    }


    public List<Category> findAllCategories() {
        List<Category> categories = this.warehouseAggregationRepositoryInterface.findAllCategories();

        log.info("Found " + categories.size() + " categories.");
        return categories;
    }

    public List<Supplier> findAllSuppliers() {
        List<Supplier> suppliers = this.warehouseAggregationRepositoryInterface.findAllSuppliers();
        log.info("Found " + suppliers.size() + " suppliers.");
        return suppliers;
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