package ehu.isad.model;

public class Liburua {

    private String isbn;
    private String egilea;
    private String data;
    private String izenburua;
    private String filename;

    public String getIsbn() {
        return isbn;
    }

    public String getEgilea() {
        return egilea;
    }

    public String getData() {
        return data;
    }

    public String getIzenburua() {
        return izenburua;
    }

    public String getFilename() {
        return filename;
    }

    public Liburua(String isbn, String egilea, String data, String izenburua, String filename) {
        this.isbn = isbn;
        this.egilea = egilea;
        this.data = data;
        this.izenburua = izenburua;
        this.filename = filename;
    }
}
