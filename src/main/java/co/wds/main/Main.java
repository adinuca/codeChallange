package co.wds.main;

import co.wds.config.MyConfiguration;
import co.wds.resource.DeviceResource;
import co.wds.service.DeviceService;
import io.dropwizard.Application;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by adina on 21.07.2015.
 */
public class Main extends Application<MyConfiguration> {


    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

    @Override
    public void initialize(Bootstrap<MyConfiguration> bootstrap) {

    }

    @Override
    public void run(MyConfiguration configuration, Environment environment) throws Exception {
        JerseyEnvironment jerseyEnvironment = environment.jersey();
        jerseyEnvironment.register(new DeviceResource(new DeviceService("savedDevices.json")));
    }
}
