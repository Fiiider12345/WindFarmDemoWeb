package sk.fri.uniza.views;

import sk.fri.uniza.auth.Role;
import sk.fri.uniza.core.User;

import javax.ws.rs.core.UriInfo;

public class NewDeviceView extends MaterializePage<MaterializeHeader, MaterializeFooter> {
    private final User loginUser;
    private final String toastMsg;


    public NewDeviceView(UriInfo uriInfo, User loginUser, String toastMsg) {
        super("new_device.ftl", uriInfo, new MaterializeHeader(loginUser, "Nove zariadenie", true), new MaterializeFooter());
        this.loginUser = loginUser;
        this.toastMsg = toastMsg;
    }

    public String getToastMsg() {
        return toastMsg;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public Role getSystemRoles() {
        return Role.getInstance();
    }
}