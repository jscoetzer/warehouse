package za.co.eblocks.assessment.warehouse.handler;

import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import za.co.eblocks.assessment.warehouse.model.Category;
import za.co.eblocks.assessment.warehouse.model.Product;
import za.co.eblocks.assessment.warehouse.service.WarehouseService;

import java.net.URI;

@Component
public class ProductHandler {

    private final WarehouseService warehouseService;

    public ProductHandler(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    public Mono<ServerResponse> getById(ServerRequest r) {
        return defaultReadResponse(this.warehouseService.get(id(r)));
    }

    public Mono<ServerResponse> findAll(ServerRequest r) {
        return defaultReadResponse(this.warehouseService.findAll());
    }

    public Mono<ServerResponse> deleteById(ServerRequest r) {
        return defaultReadResponse(this.warehouseService.delete(id(r)));
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        Flux<Product> flux = request
            .bodyToFlux(Product.class)
            .flatMap(toWrite -> this.warehouseService.create(toWrite));
        return defaultWriteResponse(flux);
    }

    private static Mono<ServerResponse> defaultWriteResponse(Publisher<Product> profiles) {
        return Mono
            .from(profiles)
            .flatMap(p -> ServerResponse
                .created(URI.create("/products/" + p.getId()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .build()
            );
    }

    private static Mono<ServerResponse> defaultReadResponse(Publisher<Product> products) {
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .body(products, Product.class);
    }

    private static String id(ServerRequest r) {
        return r.pathVariable("id");
    }
}
