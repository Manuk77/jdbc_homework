package org.example.hibernate_homework.jdbc_homework.homework_hibernate;

import javax.persistence.*;

@Entity
public class PassengersH {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private long passengerId;
    @Column(name = "passenger_name", nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50)
    private String phone;
    @Column(nullable = false, length = 50)
    private String country;
    @Column(nullable = false, length = 50)
    private String city;

    public PassengersH(long passengerId, String name, String phone, String country, String city) {
        this.passengerId = passengerId;
        this.name = name;
        this.phone = phone;
        this.country = country;
        this.city = city;
    }

    public PassengersH(){}

    public void setPassenger_id(long passenger_id) {
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
