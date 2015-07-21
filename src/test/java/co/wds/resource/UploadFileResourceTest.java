package co.wds.resource;

import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

/**
 * Created by adina on 21.07.2015.
 */
public class UploadFileResourceTest {

    private UploadFileResource uploadFileResource = new UploadFileResource();
    public void shouldUploadFile(){
        Response response  = uploadFileResource.uploadDevices(null);
    }
}