package za.co.eblocks.assessment.warehouse.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import za.co.eblocks.assessment.warehouse.model.Product;


public interface WarehouseReactiveRepository extends ReactiveMongoRepository<Product, String> {
}
