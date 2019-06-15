package sk.fri.uniza.resources;

import io.dropwizard.auth.Auth;
import io.dropwizard.views.View;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import sk.fri.uniza.WindFarmDemoApplication;
import sk.fri.uniza.api.Paged;
import sk.fri.uniza.api.Person;
import sk.fri.uniza.auth.Role;
import sk.fri.uniza.auth.Session;
import sk.fri.uniza.auth.Sessions;
import sk.fri.uniza.core.Device;
import sk.fri.uniza.core.DeviceBuilder;
import sk.fri.uniza.core.User;
import sk.fri.uniza.views.DevicesView;
import sk.fri.uniza.views.NewDeviceView;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Path("/devices")
public class DeviceResource {

    final Logger myLogger = LoggerFactory.getLogger(this.getClass());
    private Sessions sessionDao;

    public DeviceResource(Sessions sessionDao) {
        this.sessionDao = sessionDao;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed({Role.ADMIN})
    public View getDeviceTable(@Auth User user, @Context UriInfo uriInfo, @Context HttpHeaders headers, @QueryParam("page") Integer page) {
        String sessionStr = headers.getCookies().get(Sessions.COOKIE_SESSION).getValue();
        Optional<Session> sessionOptional = sessionDao.get(sessionStr);
        sessionOptional.orElseThrow(() -> new WebApplicationException());
        Session session = sessionOptional.get();

        try {
            // Get user info
            Person personLogin = null;
            Response<Person> personResponse = WindFarmDemoApplication.getWindFarmServis().getPerson(session.getBearerToken(), user.getId()).execute();
            if (personResponse.isSuccessful()) {
                personLogin = personResponse.body();
            }

            Response<Paged<List<Device>>> execute = WindFarmDemoApplication.getWindFarmServis().getPagedDevice("Bearer " + session.getToken(), 10, page).execute();
            if (execute.isSuccessful()) {
                return new DevicesView(uriInfo, execute.body().getData(), execute.body(), personLogin);
            }
            return null;

        } catch (IOException e) {
            e.printStackTrace();
            throw new WebApplicationException(e);
        }

    }

    @GET
    @Path("/device-delete")
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed({Role.USER_READ_ONLY, Role.ADMIN})
    public javax.ws.rs.core.Response deviceDelete(@Auth User user, @Context UriInfo uriInfo, @Context HttpHeaders headers, @QueryParam("id") Long deviceID, @QueryParam("page") Integer page) {

        if (deviceID == null) return null;

        if (!user.getRoles().contains(Role.ADMIN) /*|| user.getId() == dataID*/)
            throw new WebApplicationException(javax.ws.rs.core.Response.Status.UNAUTHORIZED);

        Session session = sessionDao.getSession(headers);

        Response<Void> response;
        try {

            response = WindFarmDemoApplication.getWindFarmServis().deleteDevice(session.getBearerToken(), deviceID).execute();
            if (response.isSuccessful()) {
                URI uri = UriBuilder.fromPath("/devices")
                        .queryParam("page", page)
                        .build();
                return javax.ws.rs.core.Response.seeOther(uri)
                        .build();

            }
            throw new WebApplicationException(response.code());
        } catch (IOException e) {
            e.printStackTrace();
            throw new WebApplicationException(e);
        }

    }

    @GET
    @Path("/new-device")
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed(Role.ADMIN)
    public NewDeviceView newDevice(@Auth User user, @Context UriInfo uriInfo, @Context HttpHeaders headers) {
        return new NewDeviceView(uriInfo, user, null);
    }

    @POST
    @Path("/new-device")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed({Role.ADMIN, Role.USER_READ_ONLY})
    public javax.ws.rs.core.Response newDevice(@Auth User user, @Context UriInfo uriInfo, @Context HttpHeaders headers,
                                             @NotEmpty @FormParam("name") String name,
                                             @NotEmpty @FormParam("content") String content) {

        Session session = sessionDao.getSession(headers);
        Response<Device> deviceResponse;
        try {


            Device deviceToBeSaved = new DeviceBuilder()
                    .setName(name)
                    .setContent(content)
                    .createDevice();


            deviceResponse = WindFarmDemoApplication.getWindFarmServis()
                    .saveDevices(session.getBearerToken(), deviceToBeSaved)
                    .execute();

            if (!deviceResponse.isSuccessful())
                throw new WebApplicationException(deviceResponse.errorBody().string(), deviceResponse.code());

            URI uri = UriBuilder.fromPath("/devices")
                    .build();

            return javax.ws.rs.core.Response.seeOther(uri)
                    .build();

            // If password has changed save it!
            /*Response response = WindFarmDemoApplication.getWindFarmServis().saveNewPassword(session.getBearerToken(), personResponse.body().getId(), password).execute();


            if (response.isSuccessful()) {
                URI uri = UriBuilder.fromPath("/persons")
                        .build();

                return javax.ws.rs.core.Response.seeOther(uri)
                        .build();
            } else {
                // If password cannot be saved roll back new person
                WindFarmDemoApplication.getWindFarmServis().deletePerson(session.getBearerToken(), personResponse.body().getId()).execute();
                throw new WebApplicationException(response.errorBody().string(), response.code());
            }*/

        } catch (IOException e) {
            e.printStackTrace();
            throw new WebApplicationException(e);
        }

    }

    /*@POST
    @Path("/device-info")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed({Role.ADMIN, Role.USER_READ_ONLY})
    public DeviceView setDeviceInfo(@Auth User user, @Context UriInfo uriInfo, @Context HttpHeaders headers,
                                @FormParam("id") Long id,
                                    @NotEmpty @FormParam("name") String name,
                                    @NotEmpty @FormParam("content") String content) {

        Session session = sessionDao.getSession(headers);
        Response<Device> deviceResponse;
        try {

            // If password has changed save it!
            //if (password != null && !password.isEmpty()) {
            //    WindFarmDemoApplication.getWindFarmServis().saveNewPassword(session.getBearerToken(), id, password).execute();
            //}

            deviceResponse = WindFarmDemoApplication.getWindFarmServis().getDevice(session.getBearerToken(), id).execute();
            if (deviceResponse.isSuccessful()) {
                Device deviceToBeSaved = deviceResponse.body();

                deviceToBeSaved.setName(name);
                deviceToBeSaved.setContent(content);

                //if (user.getRoles().contains(Role.ADMIN)) {
                //    if (roles != null && !roles.isEmpty()) {
                //        dataToBeSaved.setRoles(roles);
                //    }
                //}

                deviceResponse = WindFarmDemoApplication.getWindFarmServis()
                        .saveDevices(session.getBearerToken(), deviceToBeSaved)
                        .execute();

                if (deviceResponse.isSuccessful()) {
                    return new DeviceView(uriInfo, user, deviceToBeSaved, "Zmeny boli uložené");
                }
            }

            throw new WebApplicationException(deviceResponse.errorBody().string(), deviceResponse.code());
        } catch (IOException e) {
            e.printStackTrace();
            throw new WebApplicationException(e);
        }

    }*/
}
