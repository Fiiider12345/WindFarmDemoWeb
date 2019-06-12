package sk.fri.uniza.core;

public class Device {

    private Long id;
    private String content;

    public Device() {
    }

    public Device(String content) {
        this.content = content;
    }

    public Device(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
