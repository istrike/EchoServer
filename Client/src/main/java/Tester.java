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
    public void start(Future<Void> startFuture){
        Context context = vertx.getOrCreateContext();
        Long startTime = System.currentTimeMillis();
        HttpClient httpClient = vertx.createHttpClient();
        String endpoint = context.config().getString("endpoint");
        try{
            URL url = new URL(endpoint);
            httpClient.getNow(url.getPort(),url.getHost(),url.getPath(),response -> {
                response.bodyHandler(body -> {
//                    log.info("Got data " + body.toString());
                    log.info("time usage:" + Long.toString(System.currentTimeMillis() - startTime) + "ms");
                });
            });
        }
        catch (MalformedURLException e){
            log.info(e.getMessage());
        }

        startFuture.complete();
    }
}
