import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.impl.RouterImpl;
import lombok.extern.slf4j.Slf4j;


@Slf4j
class IndexHandler extends RouterImpl {

    public IndexHandler(Vertx vertx) {
        super(vertx);
        get("/").handler(this::GetIndex);

    }

    public void GetIndex(RoutingContext rc) {
        log.info("write pong");
        rc.response().end("pong");
    }


}
