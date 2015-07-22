package co.wds.service;

import junit.framework.TestCase;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@RunWith(BlockJUnit4ClassRunner.class)
public class DeviceServiceTest {

    public static final String TEST_FILE_JSON = "testFile.json";
    private DeviceService deviceService = new DeviceService(TEST_FILE_JSON);

    @Test
    @Ignore
    public void shouldSaveContentsOfFileToDisk() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("devicesTest.json");

        deviceService.saveDevices(inputStream);
        InputStream result = deviceService.getDevices();
        assertThat(IOUtils.contentEquals(inputStream, result), is(true));
    }

    @After
    public void tearDown() throws IOException {
        Files.delete(Paths.get(TEST_FILE_JSON));
    }

}