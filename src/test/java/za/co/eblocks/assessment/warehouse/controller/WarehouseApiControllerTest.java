package za.co.eblocks.assessment.warehouse.controller;

import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Flux;
import za.co.eblocks.assessment.warehouse.model.Category;
import za.co.eblocks.assessment.warehouse.model.Product;
import za.co.eblocks.assessment.warehouse.model.Supplier;
import za.co.eblocks.assessment.warehouse.service.WarehouseService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@WebMvcTest
public class WarehouseApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /*@Test
    public void indexShouldReturnProductsPage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/index",
                String.class)).contains("<h1>Products</h1>");
    }*/

        @Autowired
        private MockMvc mvc;

        @Autowired
        private WarehouseService warehouseService;

        @Test
        public void testGivenProducts_whenGetProducts_thenReturnProductList() throws Exception{

            Product product = new Product().builder()
                    .name("MVC Test Product")
                    .category(new Category("TestCat"))
                    .supplier(new Supplier("TestSup"))
                    .build();

            Flux<Product> productList = Flux.just(product);
            given(warehouseService.findAll()).willReturn(productList);

            mvc.perform(get("/products"))
                    .andExpect(status().isOk());
        }
}
