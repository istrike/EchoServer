import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.cli.Argument;
import io.vertx.core.cli.CLI;
import io.vertx.core.cli.CommandLine;
import io.vertx.core.cli.Option;
import io.vertx.ext.shell.command.CommandBuilder;

public class EntryPoint {

    public static void main(String[] args) {
//        CLI cli = CLI.create("start")
//                .setSummary("start Tester")
//                .addOption(new Option().setLongName("cores")
//                        .setShortName("c").setFlag(true).setDescription("current cpu cores usage"))
//                .addOption(new Option().setLongName("threads").setShortName("t").setFlag(true)
//                        .setDescription("threads concurrency"))
//                .addOption(new Option().setLongName("url").setShortName("u").setFlag(true)
//                        .setDefaultValue("endpoint"));
//        StringBuilder builder = new StringBuilder();
//        CommandBuilder command = CommandBuilder.command(cli);
//        cli.usage(builder);
//        Vertx vertx = Vertx.vertx();
//        DeploymentOptions options = new DeploymentOptions()
//                .setWorker(true)
//                .setInstances(Runtime.getRuntime().availableProcessors() * 2)
//                .setWorkerPoolSize(Runtime.getRuntime().availableProcessors());
//
//        vertx.deployVerticle("Tester", options);
        CLI cli = CLI.create("copy")
                .setSummary("A command line interface to copy files.")
                .addOption(new Option()
                        .setLongName("directory")
                        .setShortName("R")
                        .setDescription("enables directory support")
                        .setFlag(true))
                .addArgument(new Argument()
                        .setIndex(0)
                        .setDescription("The source")
                        .setArgName("source"))
                .addArgument(new Argument()
                        .setIndex(0)
                        .setDescription("The destination")
                        .setArgName("target"));

        StringBuilder builder = new StringBuilder();
        cli.usage(builder);
    }
}
