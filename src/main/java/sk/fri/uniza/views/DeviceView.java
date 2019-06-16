package sk.fri.uniza.views;

import sk.fri.uniza.core.Device;
import sk.fri.uniza.core.User;

import javax.ws.rs.core.UriInfo;

public class DeviceView extends MaterializePage<MaterializeHeader, MaterializeFooter> {
    private final User loginUser;
    private final Device device;
    private final String toastMsg;

    public DeviceView(UriInfo uriInfo, User loginUser, Device device, String toastMsg) {
        super("device.ftl", uriInfo, new MaterializeHeader(loginUser, "Device Info", true), new MaterializeFooter());
        this.loginUser = loginUser;
        this.device = device;
        this.toastMsg = toastMsg;
    }

    public String getToastMsg() {
        return toastMsg;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public Device getDevice() {
        return device;
    }
}
