package za.co.eblocks.assessment.warehouse.events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import reactor.core.publisher.FluxSink;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

@Component
public class ProductCreatedEventPublisher implements
        ApplicationListener<ProductCreatedEvent>,
        Consumer<FluxSink<ProductCreatedEvent>> {

    private final Executor executor;
    private final BlockingQueue<ProductCreatedEvent> queue = new LinkedBlockingQueue<>();

    public ProductCreatedEventPublisher(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void onApplicationEvent(ProductCreatedEvent event) {
        this.queue.offer(event);
    }

     @Override
    public void accept(FluxSink<ProductCreatedEvent> sink) {
        this.executor.execute(() -> {
            while (true)
                try {
                    ProductCreatedEvent event = queue.take();
                    sink.next(event);
                }
                catch (InterruptedException e) {
                    ReflectionUtils.rethrowRuntimeException(e);
                }
        });
    }
}
