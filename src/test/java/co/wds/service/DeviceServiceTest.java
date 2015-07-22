package co.wds.service;

import co.wds.model.Device;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@RunWith(BlockJUnit4ClassRunner.class)
public class DeviceServiceTest {

    public static final String TEST_FILE_JSON = "devicesTest.json";
    private DeviceService deviceService = new DeviceService();
    private InputStream inputStream;

    @Before
    public void setUp() {
        inputStream = this.getClass().getClassLoader().getResourceAsStream(TEST_FILE_JSON);
    }

    @Test
    public void shouldStoreContentsOfFile() throws IOException {
        deviceService.saveDevices(inputStream);
        Set<Device> result = deviceService.getDevices();

        assertThat(result.size(), is(3));
    }

    @Test
    public void shouldReturnSameDevices() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(TEST_FILE_JSON);

        deviceService.saveDevices(inputStream);
        Set<Device> result1 = deviceService.getDevices();
        Set<Device> result2 = deviceService.getDevices();
        Set<Device> result3 = deviceService.getDevices();

        assertThat(result1, is(equalTo(result2)));
        assertThat(result2, is(equalTo(result3)));
        inputStream.close();
    }

    @Test
    public void shouldReturnDeviceByName() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(TEST_FILE_JSON);

        deviceService.saveDevices(inputStream);
        String brand = "Mockia";
        String model = "5800";
        String name = brand+" "+model;
        Set<Device> result = deviceService.getDevices(name);

        assertThat(result.size(), is(1));
        Device device = (Device) result.toArray()[0];
        assertThat(device.getBrand(), is(equalTo(brand)));
        assertThat(device.getModel(), is(equalTo(model)));
    }

    @After
    public void tearDown() throws IOException {
        if (inputStream != null) {
            inputStream.close();
        }
    }
}