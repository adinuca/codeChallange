package co.wds.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.InputStream;

/**
 * Created by adina on 21.07.2015.
 */

@Path("/devices")
public class UploadFileResource {

    @GET
    public void getDevices(){

    }

    @POST
    public Response uploadDevices(InputStream devicesFile){
        return Response.ok().build();
    }


}
