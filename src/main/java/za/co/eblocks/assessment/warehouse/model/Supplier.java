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
public class Supplier {

    String name;
    String contactName;
    String contactTitle;
    String address;
    String city;
    String region;
    String postalCode;
    String country;
    String phoneNumber;
    String faxNumber;
    String homePageUrl;

    public Supplier(String name) {

        this.name = name;
    }
}
