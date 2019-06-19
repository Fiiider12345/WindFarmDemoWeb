package sk.fri.uniza.core;

import java.util.Calendar;

/**
 * Definuje objekt data, konstruktory, atributy a funkcie tak ako budu ulozene v databaze
 * @author Pecho
 */
public class Data {

    private Long id;
    private Float value;
    private int idDevice;
    private String dateOfStart;

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
     * @param idDevice typ int
     */
    public Data(int idDevice) {
        this.idDevice = idDevice;
        Calendar cal=Calendar.getInstance();
        dateOfStart = cal.get(Calendar.DAY_OF_MONTH)+"."+(cal.get(Calendar.MONTH)+1)+"."+cal.get(Calendar.YEAR)+
                "  " + cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);
    }

    /**
     * Konstruktor, inicializuje premennu dateOfStart a parametre value, idDevice
     * @param value Float
     * @param idDevice int
     */
    public Data(Float value, int idDevice) {
        this.value = value;
        this.idDevice = idDevice;
        Calendar cal=Calendar.getInstance();
        dateOfStart = cal.get(Calendar.DAY_OF_MONTH)+"."+(cal.get(Calendar.MONTH)+1)+"."+cal.get(Calendar.YEAR)+
                "  " + cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);
    }

    /**
     * Konstruktor, inicializuje premennu dateOfStart a parametre id, value, idDevice
     * @param id Long
     * @param value Float
     * @param idDevice int
     */
    public Data(Long id, Float value, int idDevice) {
        this.id = id;
        this.value = value;
        this.idDevice = idDevice;
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
}
