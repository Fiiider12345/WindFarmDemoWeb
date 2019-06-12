package sk.fri.uniza.views;

import sk.fri.uniza.api.Paged;
import sk.fri.uniza.api.Person;
import sk.fri.uniza.core.Data;
import sk.fri.uniza.core.User;

import javax.ws.rs.core.UriInfo;
import java.util.List;

public class DatasView extends MaterializePage<MaterializeHeader, MaterializeFooter> {

    private final List<Data> datas;
    private final Paged paged;
    private final User loginUser;

    public DatasView(UriInfo uriInfo, List<Data> datas, Paged paged, Person loginUser) {
        super("datas_table.ftl", uriInfo, new MaterializeHeader(loginUser, "Users", true), new MaterializeFooter());
        this.datas = datas;
        this.paged = paged;
        this.loginUser = loginUser;
    }

    public List<Data> getDatas() {
        return datas;
    }

    public Paged getPaged() {
        return paged;
    }

    public User getLoginUser() {
        return loginUser;
    }
}
