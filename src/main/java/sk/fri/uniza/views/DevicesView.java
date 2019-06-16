package sk.fri.uniza.views;

import sk.fri.uniza.api.Paged;
import sk.fri.uniza.api.Person;
import sk.fri.uniza.core.Device;
import sk.fri.uniza.core.User;

import javax.ws.rs.core.UriInfo;
import java.util.List;

public class DevicesView extends MaterializePage<MaterializeHeader, MaterializeFooter> {

    private final List<Device> devices;
    private final Paged paged;
    private final User loginUser;

    public DevicesView(UriInfo uriInfo, List<Device> devices, Paged paged, Person loginUser) {
        super("devices_table.ftl", uriInfo, new MaterializeHeader(loginUser, "Zariadenia", true), new MaterializeFooter());
        this.devices = devices;
        this.paged = paged;
        this.loginUser = loginUser;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public Paged getPaged() {
        return paged;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public int getDevicesNumber() {
        return devices.size();
    }
}
