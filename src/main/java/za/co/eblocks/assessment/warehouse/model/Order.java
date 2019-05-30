package za.co.eblocks.assessment.warehouse.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Order {
    int orderId;
    int quantity;
    double unitPrice;
    double discount;

    Product product;
}
