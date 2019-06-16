package sk.fri.uniza.views;

import sk.fri.uniza.core.Data;
import sk.fri.uniza.core.User;

import javax.ws.rs.core.UriInfo;

public class DataView extends MaterializePage<MaterializeHeader, MaterializeFooter> {
    private final User loginUser;
    private final Data data;
    private final String toastMsg;

    public DataView(UriInfo uriInfo, User loginUser, Data data, String toastMsg) {
        super("data.ftl", uriInfo, new MaterializeHeader(loginUser, "Data Info", true), new MaterializeFooter());
        this.loginUser = loginUser;
        this.data = data;
        this.toastMsg = toastMsg;
    }

    public String getToastMsg() {
        return toastMsg;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public Data getData() {
        return data;
    }
}
