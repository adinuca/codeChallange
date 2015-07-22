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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class DevicesDeserializerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnValidDevices() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream devicesFile = this.getClass().getClassLoader().getResourceAsStream("deserializationSuccesful.json");
        Set<Device> devices = mapper.readValue(devicesFile, new TypeReference<Set<Device>>() {
        });

        assertThat("Three devices were read", devices.size(), is(4));
    }

    @Test
    public void shouldReadDeviceWithMissingAttribute() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream f = this.getClass().getClassLoader().getResourceAsStream("attributeMissing.json");
        Set<Device> devices = mapper.readValue(f, new TypeReference<Set<Device>>() {
        });

        assertEquals("Device was read successfully", 1, devices.size());
        for (Device device : devices) {
            assertEquals("There are no attributes for device", 0, device.getAttributes().size());
        }
    }

    @Test
    public void attributeNameNullAttributeValueNotNullTest() throws IOException {
        thrown.expect(IOException.class);
        
        ObjectMapper mapper = new ObjectMapper();
        InputStream f = this.getClass().getClassLoader().getResourceAsStream("attributesInvalid.json");

        mapper.readValue(f, new TypeReference<Set<Device>>() {
        });
    }

    @Test
    public void brandNullTest() throws IOException {
        thrown.expect(IOException.class);
        
        ObjectMapper mapper = new ObjectMapper();
        InputStream f = this.getClass().getClassLoader().getResourceAsStream("brandNull.json");
        mapper.readValue(f, new TypeReference<Set<Device>>() {
        });
    }

    @Test
    public void brandEmptyTest() throws IOException {
        thrown.expect(IOException.class);

        ObjectMapper mapper = new ObjectMapper();
        InputStream f = this.getClass().getClassLoader().getResourceAsStream("brandEmpty.json");
        mapper.readValue(f, new TypeReference<Set<Device>>() {
        });
    }

    @Test
    public void modelEmptyTest() throws IOException {
        thrown.expect(IOException.class);
        
        ObjectMapper mapper = new ObjectMapper();
        InputStream f = this.getClass().getClassLoader().getResourceAsStream("modelEmpty.json");
        mapper.readValue(f, new TypeReference<Set<Device>>() {
        });
    }

    @Test
    public void modelNullTest() throws IOException {
        thrown.expect(IOException.class);

        ObjectMapper mapper = new ObjectMapper();
        InputStream f = this.getClass().getClassLoader().getResourceAsStream("modelNull.json");
        mapper.readValue(f, new TypeReference<Set<Device>>() {
        });
    }

    @Test
    public void modelValueToLongTest() throws IOException {
        thrown.expect(IOException.class);

        ObjectMapper mapper = new ObjectMapper();
        InputStream f = this.getClass().getClassLoader().getResourceAsStream("modelLengthInvalid.json");
        mapper.readValue(f, new TypeReference<Set<Device>>() {
        });
    }

    @Test
    public void brandValueToLongTest() throws IOException {
        thrown.expect(IOException.class);
        
        ObjectMapper mapper = new ObjectMapper();
        InputStream f = this.getClass().getClassLoader().getResourceAsStream("brandLengthInvalid.json");
        mapper.readValue(f, new TypeReference<Set<Device>>() {
        });
    }

    @Test
    public void attributeNameToLongTest() throws IOException {
        thrown.expect(IOException.class);

        ObjectMapper mapper = new ObjectMapper();
        InputStream f = this.getClass().getClassLoader().getResourceAsStream("attributeNameInvalid.json");
        mapper.readValue(f, new TypeReference<Set<Device>>() {
        });
    }

    @Test
    public void attributeValueToLongTest() throws IOException {
        thrown.expect(IOException.class);

        ObjectMapper mapper = new ObjectMapper();
        InputStream f = this.getClass().getClassLoader().getResourceAsStream("attributeValueInvalid.json.json");
        mapper.readValue(f, new TypeReference<Set<Device>>() {
        });
    }

}
