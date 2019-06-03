package za.co.eblocks.assessment.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    Category category;
    Supplier supplier;

    @Id
    String id;
    String name;
    double quantityPerUnit;
    double unitPrice;
    int unitsInStock;
    int unitsOrOrder;
    int reorderLevel;
    boolean discontinued;

    public Product(String name, Category category, Supplier supplier) {
        this.category = category;
        this.supplier = supplier;
        this.name = name;
    }
}
