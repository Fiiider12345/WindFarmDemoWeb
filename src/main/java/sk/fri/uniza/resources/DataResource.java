package sk.fri.uniza.resources;

import io.dropwizard.auth.Auth;
import io.dropwizard.views.View;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import sk.fri.uniza.WindFarmDemoApplication;
import sk.fri.uniza.api.Paged;
import sk.fri.uniza.api.Person;
import sk.fri.uniza.auth.Role;
import sk.fri.uniza.auth.Session;
import sk.fri.uniza.auth.Sessions;
import sk.fri.uniza.core.Data;
import sk.fri.uniza.core.DataBuilder;
import sk.fri.uniza.core.User;
import sk.fri.uniza.views.DataView;
import sk.fri.uniza.views.DatasView;
import sk.fri.uniza.views.NewDataView;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("/datas")
public class DataResource {

    final Logger myLogger = LoggerFactory.getLogger(this.getClass());
    private Sessions sessionDao;


    public DataResource(Sessions sessionDao) {
        this.sessionDao = sessionDao;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed({Role.ADMIN, Role.USER_READ_ONLY})
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

            Response<Paged<List<Data>>> execute = WindFarmDemoApplication.getWindFarmServis().getPagedData("Bearer " + session.getToken(), 10, page).execute();
            if (execute.isSuccessful()) {
                return new DatasView(uriInfo, execute.body().getData(), execute.body(), personLogin);
            }
            return null;

        } catch (IOException e) {
            e.printStackTrace();
            throw new WebApplicationException(e);
        }

    }


    @GET
    @Path("/data-delete")
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed({Role.USER_READ_ONLY, Role.ADMIN})
    public javax.ws.rs.core.Response dataDelete(@Auth User user, @Context UriInfo uriInfo, @Context HttpHeaders headers, @QueryParam("id") Long dataID, @QueryParam("page") Integer page) {

        if (dataID == null) return null;

        if (!user.getRoles().contains(Role.ADMIN) /*|| user.getId() == dataID*/)
            throw new WebApplicationException(javax.ws.rs.core.Response.Status.UNAUTHORIZED);

        Session session = sessionDao.getSession(headers);

        Response<Void> response;
        try {

            response = WindFarmDemoApplication.getWindFarmServis().deleteData(session.getBearerToken(), dataID).execute();
            if (response.isSuccessful()) {
                URI uri = UriBuilder.fromPath("/datas")
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
    @Path("/new-data")
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed({Role.ADMIN, Role.USER_READ_ONLY})
    public NewDataView newData(@Auth User user, @Context UriInfo uriInfo, @Context HttpHeaders headers) {
        return new NewDataView(uriInfo, user, null);
    }

    @POST
    @Path("/new-data")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed({Role.ADMIN, Role.USER_READ_ONLY})
    public javax.ws.rs.core.Response newData(@Auth User user, @Context UriInfo uriInfo, @Context HttpHeaders headers,
                                             @Range(min=0, max=90) @NotNull @FormParam("idDevice") Integer idDevice) {

        Session session = sessionDao.getSession(headers);
        Response<Data> dataResponse;
        try {

            Data dataToBeSaved = new DataBuilder()
                    .setValue(52.0f)
                    .setIdDevice(idDevice)
                    .createData();


            dataResponse = WindFarmDemoApplication.getWindFarmServis()
                    .saveDatas(session.getBearerToken(), dataToBeSaved)
                    .execute();

            if (!dataResponse.isSuccessful())
                throw new WebApplicationException(dataResponse.errorBody().string(), dataResponse.code());

            URI uri = UriBuilder.fromPath("/datas")
                    .build();

            return javax.ws.rs.core.Response.seeOther(uri)
                    .build();

        } catch (IOException e) {
            e.printStackTrace();
            throw new WebApplicationException(e);
        }

    }

    @POST
    @Path("/data-info")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed({Role.ADMIN, Role.USER_READ_ONLY})
    public DataView setDataInfo(@Auth User user, @Context UriInfo uriInfo, @Context HttpHeaders headers,
                                    @FormParam("id") Long id,
                                @NotEmpty @FormParam("value") Float value,
                                @NotEmpty @FormParam("idDevice") int idDevice) {

        Session session = sessionDao.getSession(headers);
        Response<Data> dataResponse;
        try {

            dataResponse = WindFarmDemoApplication.getWindFarmServis().getData(session.getBearerToken(), id).execute();
            if (dataResponse.isSuccessful()) {
                Data dataToBeSaved = dataResponse.body();

                dataToBeSaved.setValue(value);
                dataToBeSaved.setIdDevice(idDevice);

                dataResponse = WindFarmDemoApplication.getWindFarmServis()
                        .saveDatas(session.getBearerToken(), dataToBeSaved)
                        .execute();

                if (dataResponse.isSuccessful()) {
                    return new DataView(uriInfo, user, dataToBeSaved, "Zmeny boli uložené");
                }
            }

            throw new WebApplicationException(dataResponse.errorBody().string(), dataResponse.code());
        } catch (IOException e) {
            e.printStackTrace();
            throw new WebApplicationException(e);
        }

    }
}
