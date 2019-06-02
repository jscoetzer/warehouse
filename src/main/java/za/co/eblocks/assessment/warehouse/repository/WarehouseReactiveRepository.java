package za.co.eblocks.assessment.warehouse.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import za.co.eblocks.assessment.warehouse.model.Category;
import za.co.eblocks.assessment.warehouse.model.Product;
import za.co.eblocks.assessment.warehouse.model.Supplier;

import java.util.List;


public interface WarehouseReactiveRepository extends ReactiveMongoRepository<Product, String> {

    public Flux<Product> findAllByCategoryNameAndSupplierName(String categoryName, String supplierName);
    public Flux<Product> findAllBySupplierName(String supplierName);
    public Flux<Product> findAllByCategoryName(String categoryName);
}
