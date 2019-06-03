package za.co.eblocks.assessment.warehouse.service;
/*
import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import za.co.eblocks.assessment.warehouse.model.Category;
import za.co.eblocks.assessment.warehouse.model.Product;
import za.co.eblocks.assessment.warehouse.model.Supplier;
import za.co.eblocks.assessment.warehouse.repository.WarehouseReactiveRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@Log4j2
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WarehouseServiceIntegrationTest {

    @Autowired
    private WarehouseService warehouseService;

    private List<Product> products;

    @BeforeAll
    public void givenTestProductsInTheDatabase(){
        products = new ArrayList<>();
        products.add(warehouseService.create(new Product().builder()
                .name("Test Product")
                .category(new Category("TestCat1"))
                .supplier(new Supplier("TestSup1"))
                .build()).block());

        products.add(warehouseService.create(new Product().builder()
                .name("Test Product Two")
                .category(new Category("TestCat2"))
                .supplier(new Supplier("TestSup2"))
                .build()).block());
        products.add(warehouseService.create(new Product().builder()
                .name("Test Product Three")
                .category(new Category("TestCat1"))
                .supplier(new Supplier("TestSup2"))
                .build()).block());
        products.add(warehouseService.create(new Product().builder()
                .name("Test Product Four")
                .category(new Category("TestCat1"))
                .supplier(new Supplier("TestSup2"))
                .build()).block());
    }

    @Test
    public void WhenFindingAll_ThenReturnAtLeastOne(){
        Flux<Product> products = warehouseService.findAll();
        assertEquals(true,products.collectList().block().size()>0);
    }

    @DisplayName("Test filtering of documents by supplier name.")
    @Test
    public void testSupplierFilter(){
        Flux<Product> products = warehouseService.findAllBySupplierName("TestSup1");
        assertEquals(1,products.collectList().block().size());
    }

    @DisplayName("Test filtering of documents by category name.")
    @Test
    public void testCategoryFilter(){
        Flux<Product> products = warehouseService.findAllByCategoryName("TestCat1");
        assertEquals(3,products.collectList().block().size());
    }

    @DisplayName("Test filtering of documents by both supplier and category name.")
    @Test
    public void testCategoryAndSupplierFilter(){

        Flux<Product> products = warehouseService.findAllByCategoryNameAndSupplierName("TestCat1","TestSup2");
        List<Product> productList = products.collectList().block();

        Boolean categoryTest = productList.stream().map(product -> product.getCategory().getName())
                .allMatch(p -> p.equals("TestCat1"));

        Boolean supplierTest = productList.stream().map(product -> product.getSupplier().getName())
                .allMatch(p -> p.equals("TestSup2"));

        assertEquals(true,categoryTest);
        assertEquals(true,supplierTest);
    }

    @AfterAll
    public void teardownTestData(){
        products.forEach(product -> {
            warehouseService.deleteById(product.getId());
        });
    }



}*/
