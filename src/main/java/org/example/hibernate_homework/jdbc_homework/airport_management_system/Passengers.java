package org.example.hibernate_homework.jdbc_homework.airport_management_system;

public class Passengers {
    private long passenger_id;
    private String name;
    private String phone;
    private String country;
    private String city;

    public Passengers(long passenger_id, String name, String phone, String country, String city) {
        this.passenger_id = passenger_id;
        this.name = name;
        this.phone = phone;
        this.country = country;
        this.city = city;
    }

    public Passengers(){}

    public void setPassenger_id(long passenger_id) {
        this.passenger_id = passenger_id;
    }

    public long getPassenger_id() {
        return passenger_id;
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
                "passenger_id=" + passenger_id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
