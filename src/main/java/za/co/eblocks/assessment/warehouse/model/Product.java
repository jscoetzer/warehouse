package za.co.eblocks.assessment.warehouse.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Product {

    Category category;
    Supplier supplier;

    String name;
    double quantityPerUnit;
    double unitPrice;
    int unitsInStock;
    int unitsOrOrder;
    int reorderLevel;
    boolean discontinued;

}
