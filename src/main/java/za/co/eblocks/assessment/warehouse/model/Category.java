package za.co.eblocks.assessment.warehouse.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Category {
    int id;
    String name;
    String description;
    String pictureUrl;
}
