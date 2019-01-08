import io.vertx.core.DeploymentOptions;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;

public class EntryPoint {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        DeploymentOptions options = new DeploymentOptions()
                .setWorker(true)
                .setInstances(Runtime.getRuntime().availableProcessors() * 2)
                .setWorkerPoolSize(Runtime.getRuntime().availableProcessors());

        vertx.deployVerticle("EchoServer", options);
    }
}
