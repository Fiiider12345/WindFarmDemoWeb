package sk.fri.uniza.core;

/**
 * Definuje objekt device, konstruktory, atributy a funkcie tak ako bude ulozeny v databaze
 * @author Pecho
 */
public class Device {

    private Long id;
    private String name;
    private String content;


    /**
     * Hibernate need default constructor
     */
    public Device() {
    }

    /**
     * Konstruktor inicializuje parametre name, content
     * @param name String
     * @param content String
     */
    public Device(String name, String content) {
        this.name = name;
        this.content = content;
    }

    /**
     * Konstruktor inicializuje parametre name, content, id
     * @param id Long
     * @param name String
     * @param content String
     */
    public Device(Long id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
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
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * setter
     * @param content String
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * getter
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setter
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }
}
