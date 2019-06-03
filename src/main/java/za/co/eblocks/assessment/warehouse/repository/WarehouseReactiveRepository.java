package za.co.eblocks.assessment.warehouse.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import za.co.eblocks.assessment.warehouse.model.Product;

public interface WarehouseReactiveRepository extends ReactiveMongoRepository<Product, String> {

    public Flux<Product> findAllByCategoryNameAndSupplierName(String categoryName, String supplierName);
    public Flux<Product> findAllBySupplierName(String supplierName);
    public Flux<Product> findAllByCategoryName(String categoryName);
    public Flux<Product> findByName(String name);

}
