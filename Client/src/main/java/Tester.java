import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetSocket;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Tester extends AbstractVerticle {

    @Override
    public void start() {
        long startTime = System.currentTimeMillis();
        Context context = vertx.getOrCreateContext();
        NetClient TcpClient = vertx.createNetClient();
        TcpClient.connect(context.config().getInteger("port"), context.config().getString("host"), res -> {
            if (res.succeeded()) {
                NetSocket socket = res.result();
                socket.handler( buffer -> {
//                    log.info("recv from server:{}",buffer.toString());
                });
                socket.write("ping");
                log.info("process time :{}",System.currentTimeMillis()-startTime);
            } else {
                log.info("Failed to connect:{} " ,res.cause().getCause());
            }
        });

    }
}
