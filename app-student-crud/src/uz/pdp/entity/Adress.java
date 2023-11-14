package uz.pdp.entity;

public class Adress {
    private Integer id;
    private String region;
    private String city;
    private String adressLine;

    public Adress(Integer id, String region, String city, String adressLine) {
        this.id = id;
        this.region = region;
        this.city = city;
        this.adressLine = adressLine;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdressLine() {
        return adressLine;
    }

    public void setAdressLine(String adressLine) {
        this.adressLine = adressLine;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "id=" + id +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", adressLine='" + adressLine + '\'' +
                '}';
    }
}
