import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.apache.commons.cli.*;

public class ClientEntryPoint {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        Options options = new Options();
        Option url = new Option("u", "url", true, "endpoint");
        url.setRequired(true);
        options.addOption(url);
        Option cores = new Option("c", "cpus", true, "endpoint");
        url.setRequired(true);
        options.addOption(cores);
        Option threads = new Option("t", "threads", true, "endpoint");
        url.setRequired(true);
        options.addOption(threads);
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
            int cpus = Integer.parseInt(cmd.getOptionValue("cpus"));
            int concurrency = Integer.parseInt(cmd.getOptionValue("threads"));
            String endpoint = cmd.getOptionValue("url");
            System.out.println("cpus:" + cpus + "," + "concurrency:" + concurrency);
            JsonObject config = new JsonObject()
                    .put("cpus", cpus)
                    .put("instances", concurrency)
                    .put("endpoint", endpoint);

            DeploymentOptions develop = new DeploymentOptions()
                    .setInstances(concurrency)
                    .setWorkerPoolSize(cpus)
                    .setWorker(true)
                    .setConfig(config);
            vertx.deployVerticle("Tester", develop);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }


}

