package za.co.eblocks.assessment.warehouse.events;

import org.springframework.context.ApplicationEvent;
import za.co.eblocks.assessment.warehouse.model.Product;

public class ProductCreatedEvent extends ApplicationEvent {
    public ProductCreatedEvent(Product source) {
        super(source);
    }
}
