package org.example.hibernate_homework.jdbc_homework.airport_management_system;

public class Passengers {
    private long passengerId;
    private String name;
    private String phone;
    private String country;
    private String city;

    public Passengers(long passengerId, String name, String phone, String country, String city) {
        this.passengerId = passengerId;
        this.name = name;
        this.phone = phone;
        this.country = country;
        this.city = city;
    }

    public Passengers(){}

    public void setPassenger_id(long passengerId) {
        this.passengerId = passengerId;
    }

    public long getPassenger_id() {
        return passengerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Passengers{" +
                "passenger_id=" + passengerId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
