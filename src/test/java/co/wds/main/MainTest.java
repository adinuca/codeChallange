package co.wds.main;

import co.wds.config.MyConfiguration;
import co.wds.resource.DeviceResource;
import io.dropwizard.setup.Environment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainTest {

    @Mock
    private Environment environment;
    @Mock
    private io.dropwizard.jersey.setup.JerseyEnvironment jersey;

    private Main main = new Main();


    @Test
    public void shouldRegisterDeviceResource() throws Exception {
        when(environment.jersey()).thenReturn(jersey);

        main.run(new MyConfiguration(), environment);

        verify(jersey).register(any(DeviceResource.class));
    }
}
