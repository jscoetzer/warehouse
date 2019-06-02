package za.co.eblocks.assessment.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
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
