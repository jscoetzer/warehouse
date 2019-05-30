package za.co.eblocks.assessment.warehouse.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import za.co.eblocks.assessment.warehouse.model.*;

public class WarehouseRepositoryImplementation implements WarehouseRepository{
    private static final Logger logger = LoggerFactory.getLogger(WarehouseRepository.class);

    public Product addProduct(Product product){
        return product;
    }

    public void removeProduct(Product product){
        logger.info("Removing");
    }

    public Flux<Product> findByCategoryAndSupplier(Category category, Supplier supplier){
        return null;
    }
}
