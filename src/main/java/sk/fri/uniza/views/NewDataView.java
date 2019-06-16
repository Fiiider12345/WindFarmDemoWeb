package sk.fri.uniza.views;

import sk.fri.uniza.auth.Role;
import sk.fri.uniza.core.User;

import javax.ws.rs.core.UriInfo;

public class NewDataView extends MaterializePage<MaterializeHeader, MaterializeFooter> {
    private final User loginUser;
    private final String toastMsg;
    //private final DevicesView devicesView;


    public NewDataView(UriInfo uriInfo, User loginUser, String toastMsg) {
        super("new_data.ftl", uriInfo, new MaterializeHeader(loginUser, "Nove data", true), new MaterializeFooter());
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
