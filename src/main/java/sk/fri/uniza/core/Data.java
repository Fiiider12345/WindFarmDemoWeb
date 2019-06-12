package sk.fri.uniza.core;

public class Data {

    private Long id;
    private Float value;
    private int idDevice;
    private String dateOfStart;

    public Data() {
    }

    public Data(Float value, int idDevice, String dateOfStart) {
        this.value = value;
        this.idDevice = idDevice;
        this.dateOfStart = dateOfStart;
    }

    public Data(Long id, Float value, int idDevice, String dateOfStart) {
        this.id = id;
        this.value = value;
        this.idDevice = idDevice;
        this.dateOfStart = dateOfStart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public int getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
    }

    public String getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(String dateOfStart) {
        this.dateOfStart = dateOfStart;
    }
}
