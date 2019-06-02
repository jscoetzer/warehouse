package za.co.eblocks.assessment.warehouse.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import za.co.eblocks.assessment.warehouse.model.Category;
import za.co.eblocks.assessment.warehouse.model.Product;
import za.co.eblocks.assessment.warehouse.model.Supplier;
import za.co.eblocks.assessment.warehouse.repository.WarehouseReactiveRepository;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class LoadDataConfiguration {

    @Bean
    ApplicationRunner init(WarehouseReactiveRepository repository) {

        List<Product> data = new ArrayList<>();
        /*
        data.add(new Product("Stinger 0M 9.5mm", new Category("Ropes"), new Supplier("Beal")));
        data.add(new Product("Antidote 90M 10mm Static", new Category("Ropes"), new Supplier("Beal")));
        data.add(new Product("Joker 70M 9.5mm", new Category("Ropes"), new Supplier("Beal")));
        data.add(new Product("Arial 60M 9.5mm", new Category("Ropes"), new Supplier("Petzl")));
        data.add(new Product("Arial 60M 9.5mm Dry-Treated", new Category("Ropes"), new Supplier("Petzl")));
        data.add(new Product("Galaxy 60M 10mm", new Category("Ropes"), new Supplier("Mammut")));
        data.add(new Product("Galaxy 70M 10mm", new Category("Ropes"), new Supplier("Mammut")));
        data.add(new Product("Galaxy 70M 10mm", new Category("Ropes"), new Supplier("Mammut")));

        data.add(new Product("Aero Team IV", new Category("Harnesses"), new Supplier("Beal")));
        data.add(new Product("Ellipse XT", new Category("Harnesses"), new Supplier("Beal")));
        data.add(new Product("Corax", new Category("Harnesses"), new Supplier("Petzl")));
        data.add(new Product("Sitta", new Category("Harnesses"), new Supplier("Petzl")));
        data.add(new Product("Viper 2", new Category("Harnesses"), new Supplier("DMM")));
        data.add(new Product("Venture", new Category("Harnesses"), new Supplier("DMM")));
        */

        return args -> {
            data.forEach( d -> {
                        log.info("Saving " + d.toString());
                        repository.save(d);
                    }
            );
        };
    }
}
