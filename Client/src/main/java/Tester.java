import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.http.HttpClient;
import lombok.extern.slf4j.Slf4j;
import sun.awt.ModalExclude;

import java.net.MalformedURLException;
import java.net.URL;


@Slf4j
public class Tester extends AbstractVerticle {

    @Override
    public void start() {
        Future.succeededFuture()
                .map(
                        v -> {
                            Context context = vertx.getOrCreateContext();
                            Long startTime = System.currentTimeMillis();
                            HttpClient httpClient = vertx.createHttpClient();
                            httpClient.get(context.config().getString("endpoint"));
                            log.info("time usage:"+ Long.toString(System.currentTimeMillis()-startTime)+"ms");
                            return 0;
                        }

                )
                .setHandler(ar -> {
                    if (ar.failed()) {
                        log.info(ar.cause().getMessage());
                    }
                });


    }
}
