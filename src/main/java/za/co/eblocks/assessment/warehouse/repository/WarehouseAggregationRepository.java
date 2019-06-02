package za.co.eblocks.assessment.warehouse.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import za.co.eblocks.assessment.warehouse.model.Category;
import za.co.eblocks.assessment.warehouse.model.Product;
import za.co.eblocks.assessment.warehouse.model.Supplier;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class WarehouseAggregationRepository implements WarehouseAggregationRepositoryInterface{

    @Autowired
    MongoTemplate mongoTemplate;


    public List<Category> findAllCategories(){
            return mongoTemplate.query(Product.class)
                    .distinct("category.name")
                    .as(Category.class)
                    .all();
    }

    public List<Supplier> findAllSuppliers(){
        return mongoTemplate.query(Product.class)
                .distinct("supplier.name")
                .as(Supplier.class)
                .all();
    }
}
