package sk.fri.uniza.resources;

import io.dropwizard.auth.Auth;
import io.dropwizard.views.View;
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
import sk.fri.uniza.core.User;
import sk.fri.uniza.views.DevicesView;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Path("/device")
public class DeviceResource {

    final Logger myLogger = LoggerFactory.getLogger(this.getClass());
    private Sessions sessionDao;

    public DeviceResource(Sessions sessionDao) {
        this.sessionDao = sessionDao;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed({Role.ADMIN})
    public View getDataTable(@Auth User user, @Context UriInfo uriInfo, @Context HttpHeaders headers, @QueryParam("page") Integer page) {
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
}
