package za.co.eblocks.assessment.warehouse.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
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
}
