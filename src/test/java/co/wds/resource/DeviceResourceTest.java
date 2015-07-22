package co.wds.resource;

import co.wds.model.Device;
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
import java.util.Collections;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

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
    public void shouldReturnEmptyListOfDevicesWhenNoneWasUploaded() throws FileNotFoundException {
        Set<Device> devices = Collections.emptySet();
        when(deviceService.getDevices()).thenReturn(devices);

        Response response = uploadFileResource.getDevices(null, null, null);

        assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
        assertThat(response.getEntity(), CoreMatchers.<Object>is(devices));
        verify(deviceService).getDevices();
    }

    @Test
    public void shouldReturnFilteredResultsOfDevicesWhenNameIsGiven() throws FileNotFoundException {
        Set<Device> devices = Collections.emptySet();
        String name = "something something";
        when(deviceService.getDevices(name)).thenReturn(devices);

        Response response = uploadFileResource.getDevices(name, null, null);

        assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
        assertThat(response.getEntity(), CoreMatchers.<Object>is(devices));
        verify(deviceService).getDevices(name);
    }

    @Test
    public void shouldReturnFilteredResultsOfDevicesWhenModelAndBrandIsGiven() throws FileNotFoundException {
        Set<Device> devices = Collections.emptySet();
        String brand = "something";
        String model = "model";
        when(deviceService.getDevices(brand, model)).thenReturn(devices);

        Response response = uploadFileResource.getDevices(null, brand, model);

        assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
        assertThat(response.getEntity(), CoreMatchers.<Object>is(devices));
        verify(deviceService).getDevices(brand, model);
    }
}