import io.vertx.core.Context;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;


@Slf4j
public class ClientEntryPoint {
    static int vertxcounter = 0;

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        Options options = new Options();
        Option port = new Option("p", "port", true, "endpoint");
        port.setRequired(true);
        options.addOption(port);
        Option host = new Option("h", "host", true, "endpoint");
        host.setRequired(true);
        options.addOption(host);
        Option cores = new Option("c", "cpus", true, "endpoint");
        cores.setRequired(true);
        options.addOption(cores);
        Option threads = new Option("t", "threads", true, "endpoint");
        threads.setRequired(true);
        options.addOption(threads);
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;
        try {
            long startTime = System.currentTimeMillis();
            cmd = parser.parse(options, args);
            int cpus = Integer.parseInt(cmd.getOptionValue("cpus"));
            int concurrency = Integer.parseInt(cmd.getOptionValue("threads"));
            int tport = Integer.parseInt(cmd.getOptionValue("port"));
            String thost = cmd.getOptionValue("host");

            log.info("cpu:{},concurrency:{},host:{},port:{}",cpus,concurrency,thost,tport);
            JsonObject config = new JsonObject()
                    .put("cpus", cpus)
                    .put("instances", concurrency)
                    .put("host", thost)
                    .put("port", tport);

            DeploymentOptions develop = new DeploymentOptions()
                    .setInstances(concurrency)
                    .setWorkerPoolSize(cpus)
                    .setWorker(true)
                    .setConfig(config);
            vertx.deployVerticle("Tester", develop, ar -> {
                if (ar.succeeded()) {
                    log.info("all successed!!! use time:{}",System.currentTimeMillis()-startTime);
                    vertx.close();

                }
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }


}

