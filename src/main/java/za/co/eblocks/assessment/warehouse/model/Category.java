package za.co.eblocks.assessment.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    String name;
    String description;
    String pictureUrl;

    public Category(String name) {
        this.name = name;
    }
}
