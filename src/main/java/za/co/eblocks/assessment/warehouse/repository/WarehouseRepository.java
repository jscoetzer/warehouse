package za.co.eblocks.assessment.warehouse.repository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import za.co.eblocks.assessment.warehouse.model.*;

@Repository
public interface WarehouseRepository {

    public Product addProduct(Product product);
    public void removeProduct(Product product);

    public Flux<Product> findByCategoryAndSupplier(Category category, Supplier supplier);
}
