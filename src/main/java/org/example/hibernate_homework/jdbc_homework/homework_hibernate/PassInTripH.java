package org.example.hibernate_homework.jdbc_homework.homework_hibernate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Pass_in_trip")
public class PassInTripH {
    @Id
    @Column(name = "pass_in_trip")
    private long passInTripId;
    @Column(name = "trip_id", nullable = false)
    private long tripId;
    @Column(name = "passenger_id", nullable = false)
    private long passengerId;
    @Column(nullable = false)
    private Timestamp date;
    @Column(nullable = false, length = 50)
    private String place;

    public PassInTripH(long tripId, long passengerId, Timestamp date, String place) {
        this.tripId = tripId;
        this.passengerId = passengerId;
        this.date = date;
        this.place = place;
    }

    public PassInTripH(){}

    public void setPassInTripId(long passInTripId) {
        this.passInTripId = passInTripId;
    }

    public long getPassInTripId() {
        return passInTripId;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public long getTripId() {
        return tripId;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
    public Timestamp getDate() {
        return this.date;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    @Override
    public String toString() {
        return "PassInTripH{" +
                "passInTripId=" + passInTripId +
                ", tripId=" + tripId +
                ", passengerId=" + passengerId +
                ", date=" + date +
                ", place='" + place + '\'' +
                '}';
    }
}
