package sk.fri.uniza.core;

import retrofit2.Response;
import sk.fri.uniza.WindFarmDemoApplication;
import sk.fri.uniza.api.Paged;
import sk.fri.uniza.auth.Session;
import sk.fri.uniza.auth.Sessions;
import sk.fri.uniza.views.DevicesView;

import javax.ws.rs.WebApplicationException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * Definuje objekt data, konstruktory, atributy a funkcie tak ako budu ulozene v databaze
 * @author Pecho
 */
public class Data {

    private Long id;
    private Float value;
    private int idDevice;
    private String dateOfStart;
    private int idUser;

    /**
     * Hibernate need default constructor
     */
    public Data() {
        Calendar cal=Calendar.getInstance();
        dateOfStart = cal.get(Calendar.DAY_OF_MONTH)+"."+(cal.get(Calendar.MONTH)+1)+"."+cal.get(Calendar.YEAR)+
                "  " + cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);
    }

    /**
     * Konstruktor, inicializuje premennu dateOfStart a parameter idDevice
     * @param idDevice int
     * @param idUser int
     */
    public Data(int idDevice, int idUser) {
        this.idDevice = idDevice;
        this.idUser = idUser;
        Calendar cal=Calendar.getInstance();
        dateOfStart = cal.get(Calendar.DAY_OF_MONTH)+"."+(cal.get(Calendar.MONTH)+1)+"."+cal.get(Calendar.YEAR)+
                "  " + cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);
    }

    /**
     * Konstruktor, inicializuje premennu dateOfStart a parametre value, idDevice
     * @param value float
     * @param idDevice int
     * @param idUser int
     */
    public Data(Float value, int idDevice, int idUser) {
        this.value = value;
        this.idDevice = idDevice;
        this.idUser=idUser;
        Calendar cal=Calendar.getInstance();
        dateOfStart = cal.get(Calendar.DAY_OF_MONTH)+"."+(cal.get(Calendar.MONTH)+1)+"."+cal.get(Calendar.YEAR)+
                "  " + cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);
    }

    /**
     * Konstruktor, inicializuje premennu dateOfStart a parametre id, value, idDevice
     * @param id long
     * @param value float
     * @param idDevice int
     * @param idUser int
     */
    public Data(Long id, Float value, int idDevice, int idUser) {
        this.id = id;
        this.value = value;
        this.idDevice = idDevice;
        this.idUser = idUser;
        Calendar cal=Calendar.getInstance();
        dateOfStart = cal.get(Calendar.DAY_OF_MONTH)+"."+(cal.get(Calendar.MONTH)+1)+"."+cal.get(Calendar.YEAR)+
                "  " + cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);
    }

    /**
     * getter
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * setter
     * @param id Long
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * getter
     * @return value
     */
    public Float getValue() {
        return value;
    }

    /**
     * setter
     * @param value Float
     */
    public void setValue(Float value) {
        this.value = value;
    }

    /**
     * getter
     * @return idDevice
     */
    public int getIdDevice() {
        return idDevice;
    }

    /**
     * setter
     * @param idDevice int
     */
    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
    }

    /**
     * getter
     * @return dateOfStart
     */
    public String getDateOfStart() {
        return dateOfStart;
    }

    /**
     * setter
     * @param dateOfStart String
     */
    public void setDateOfStart(String dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
