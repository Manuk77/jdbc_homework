package org.example.hibernate_homework.jdbc_homework.homework_hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.sql.Timestamp;
@Entity
public class TripH {
    @Column(name = "company_name")
    private long companyId;
    @Id
    @Column(name = "trip_id")
    private long tripId;
    private String airplane;
    @Column(name = "town_from")
    private String townFrom;
    @Column(name = "town_to")
    private String townTo;
    @Column(name = "time_out")
    private Timestamp timeOut;
    @Column(name = "time_in")
    private Timestamp timeIn;

    public TripH(long companyId, long tripId, String airplane, String townFrom,
                String townTo, Timestamp timeOut, Timestamp timeIn) {

        this.companyId = companyId;
        this.tripId = tripId;
        this.airplane = airplane;
        this.townFrom = townFrom;
        this.townTo = townTo;
        this.timeOut = timeOut;
        this.timeIn = timeIn;
    }

    public TripH(){}

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setTripId(long trip_id) {
        this.tripId = tripId;
    }

    public long getTripId() {
        return tripId;
    }

    public void setAirplane(String airplane) {
        this.airplane = airplane;
    }

    public String getAirplane() {
        return airplane;
    }

    public void setTownFrom(String townFrom) {
        this.townFrom = townFrom;
    }

    public String getTownFrom() {
        return townFrom;
    }

    public void setTownTo(String townTo) {
        this.townTo = townTo;
    }

    public String getTownTo() {
        return townTo;
    }

    public void setTimeOut(Timestamp timeOut) {
        this.timeOut = timeOut;
    }

    public Timestamp getTimeOut() {
        return timeOut;
    }

    public void setTimeIn(Timestamp timeIn) {
        this.timeIn = timeIn;
    }

    public Timestamp getTimeIn() {
        return timeIn;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "company_id=" + companyId +
                ", trip_id=" + tripId +
                ", airplane='" + airplane + '\'' +
                ", townFrom='" + townFrom + '\'' +
                ", townTo='" + townTo + '\'' +
                ", timeOut=" + timeOut +
                ", timeIn=" + timeIn +
                '}';
    }
}
