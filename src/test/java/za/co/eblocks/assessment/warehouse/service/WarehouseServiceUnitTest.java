package za.co.eblocks.assessment.warehouse.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import reactor.core.publisher.Flux;
import za.co.eblocks.assessment.warehouse.model.Product;
import za.co.eblocks.assessment.warehouse.repository.WarehouseReactiveRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@Log4j2
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class WarehouseServiceUnitTest {

    @Mock
    private WarehouseReactiveRepository warehouseReactiveRepository;

    @InjectMocks
    private WarehouseService warehouseService = new WarehouseService();

    @BeforeEach
    public void setMockOutput(){
        when(warehouseReactiveRepository.findAll())
                .thenReturn(
                        //Flux.just(new Product().builder().name("Test one").build()),
                        Flux.fromIterable(Arrays.asList(new Product().builder().name("Test one").build(),new Product().builder().name("Test one").build()))
                );
    }

    private List<Product> expected = Arrays.asList(new Product().builder().name("Test one").build(),
            //new Product().builder().name("Test one").build(),
            new Product().builder().name("Test one").build());

    @Test
    public void testFindAll(){
        Flux<Product> actual = warehouseService.findAll();

        assertEquals(expected, actual.collectList().block());
    }
}
