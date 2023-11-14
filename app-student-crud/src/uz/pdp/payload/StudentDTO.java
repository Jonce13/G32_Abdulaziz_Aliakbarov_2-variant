package uz.pdp.payload;

import uz.pdp.entity.Adress;
import uz.pdp.entity.Group;

import java.time.LocalDate;

public class StudentDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private long phoneNumber;
    private LocalDate birthDate;
    private Integer groupId;
    private Integer adressId;
    private String biographyFilePath;


    public StudentDTO(String firstName, String lastName, long phoneNumber, LocalDate birthDate,
                      Integer groupId, Integer adressId, String biographyFilePath) {
        this.id = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.groupId = groupId;
        this.adressId = adressId;
        this.biographyFilePath = biographyFilePath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getAdressId() {
        return adressId;
    }

    public void setAdressId(Integer adressId) {
        this.adressId = adressId;
    }

    public String getBiographyFilePath() {
        return biographyFilePath;
    }

    public void setBiographyFilePath(String biographyFilePath) {
        this.biographyFilePath = biographyFilePath;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", birthDate=" + birthDate +
                ", groupId=" + groupId +
                ", adressId=" + adressId +
                ", biographyFilePath='" + biographyFilePath + '\'' +
                '}';
    }
}
