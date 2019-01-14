import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
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
        NetServer TcpServer = vertx.createNetServer();
        TcpServer.connectHandler(socket -> {
            socket.handler(buffer -> {
                log.info("recved:{}", buffer.toString());
                socket.write("pong");
            });
        });

        TcpServer.listen(1234, res -> {
            if (res.succeeded()) {
                log.info("Server is now listening!");
            } else {
                log.info("Failed to bind!{}",res.cause().getMessage());
            }
        });
    }
}

