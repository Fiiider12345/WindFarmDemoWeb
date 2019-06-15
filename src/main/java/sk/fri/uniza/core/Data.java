package sk.fri.uniza.core;

import java.util.Calendar;

public class Data {

    private Long id;
    private Float value;
    private int idDevice;
    private String dateOfStart;

    public Data() {
        Calendar cal=Calendar.getInstance();
        dateOfStart = cal.get(Calendar.DAY_OF_MONTH)+"."+(cal.get(Calendar.MONTH)+1)+"."+cal.get(Calendar.YEAR)+
                "  " + cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);
    }

    public Data(int idDevice) {
        this.idDevice = idDevice;
        Calendar cal=Calendar.getInstance();
        dateOfStart = cal.get(Calendar.DAY_OF_MONTH)+"."+(cal.get(Calendar.MONTH)+1)+"."+cal.get(Calendar.YEAR)+
                "  " + cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);
    }

    public Data(Float value, int idDevice) {
        this.value = value;
        this.idDevice = idDevice;
        Calendar cal=Calendar.getInstance();
        dateOfStart = cal.get(Calendar.DAY_OF_MONTH)+"."+(cal.get(Calendar.MONTH)+1)+"."+cal.get(Calendar.YEAR)+
                "  " + cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);
    }

    public Data(Long id, Float value, int idDevice) {
        this.id = id;
        this.value = value;
        this.idDevice = idDevice;
        Calendar cal=Calendar.getInstance();
        dateOfStart = cal.get(Calendar.DAY_OF_MONTH)+"."+(cal.get(Calendar.MONTH)+1)+"."+cal.get(Calendar.YEAR)+
                "  " + cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);
    }

    //@JsonProperty
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //@JsonProperty
    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    //@JsonProperty
    public int getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
    }

    //@JsonProperty
    public String getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(String dateOfStart) {
        this.dateOfStart = dateOfStart;
    }
}
