package co.wds.resource;

import co.wds.service.DeviceService;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeviceResourceTest {

    @Mock
    private static DeviceService deviceService;
    @InjectMocks
    private DeviceResource uploadFileResource;
    @Mock
    private FileInputStream inputStream;

    @Test
    public void shouldUploadFile() throws IOException {
        Response response = uploadFileResource.uploadDevices(inputStream);

        assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
        verify(deviceService).saveDevices(inputStream);
    }

    @Test
    public void shouldReturnServerErrorIfTheFileCannotBeUploaded() throws IOException {
        doThrow(IOException.class).when(deviceService).saveDevices(inputStream);

        Response response = uploadFileResource.uploadDevices(inputStream);

        assertThat(response.getStatus(), is(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()));
        verify(deviceService).saveDevices(inputStream);
    }

    @Test
    public void shouldReturnDevices() throws FileNotFoundException {
        when(deviceService.getDevices()).thenReturn( inputStream);

        Response response = uploadFileResource.getDevices();

        assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
        assertThat(response.getEntity(), CoreMatchers.<Object>is(inputStream));
        verify(deviceService).getDevices();
    }

    @Test
    public void shouldReturnServerErrorIfNoDevicesFound() throws FileNotFoundException {
        when(deviceService.getDevices()).thenThrow(FileNotFoundException.class);

        Response response = uploadFileResource.getDevices();

        assertThat(response.getStatus(), is(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()));
        verify(deviceService).getDevices();
    }
}