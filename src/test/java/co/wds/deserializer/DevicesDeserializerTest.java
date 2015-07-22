package co.wds.deserializer;

import co.wds.model.Device;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DevicesDeserializerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnDevices() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream devicesFile = this.getClass().getClassLoader().getResourceAsStream("deserializationSuccesful.json");
        Set<Device> devices = mapper.readValue(devicesFile, new TypeReference<Set<Device>>() {
        });

        assertThat("Three devices were read", devices.size(), is(4));
    }

}
