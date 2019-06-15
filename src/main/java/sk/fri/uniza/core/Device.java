package sk.fri.uniza.core;

public class Device {

    private Long id;
    private String name;
    private String content;

    public Device() {
    }

    public Device(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public Device(Long id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    //@JsonProperty
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //@JsonProperty
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
