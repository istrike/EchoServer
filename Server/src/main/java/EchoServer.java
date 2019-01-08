import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EchoServer extends AbstractVerticle {


    public void start() {
        HttpServerOptions options = new HttpServerOptions().setPort(6169);
        HttpServer server = vertx.createHttpServer(options);
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.mountSubRouter("/", new IndexHandler(vertx));
        router.route().handler(ctx -> ctx.response().setStatusCode(404).write("page not found").end());
        router.route().failureHandler(ctx -> {
            log.info("Failure!", ctx.failure());
            HttpServerResponse response = ctx.response();
            try {
                throw ctx.failure();
            } catch (IllegalArgumentException e) {
                response.setStatusCode(400).end("IllegalArgument found");
            } catch (Throwable t) {
                ctx.response().setStatusCode(500)
                        .end(t.getMessage());
            }
        });
        server.requestHandler(router)
                .listen(res -> {
                    if (res.succeeded()) {
                        System.out.println("Server is now listening at " + server.actualPort());
                    } else {
                        System.out.println("Failed to bind!");
                    }
                });
    }


}
