package za.co.eblocks.assessment.warehouse.repository;

import org.springframework.context.annotation.Bean;
import za.co.eblocks.assessment.warehouse.model.Category;
import za.co.eblocks.assessment.warehouse.model.Supplier;

import java.util.List;

public interface WarehouseAggregationRepositoryInterface {

    public List<Category> findAllCategories();
    public List<Supplier> findAllSuppliers();
}
