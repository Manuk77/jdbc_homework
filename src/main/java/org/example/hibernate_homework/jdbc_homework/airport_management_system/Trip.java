package org.example.hibernate_homework.jdbc_homework.airport_management_system;

import java.sql.Timestamp;

public class Trip {
    private long companyId;
    private long tripId;
    private String airplane;
    private String townFrom;
    private String townTo;
    private Timestamp timeOut;
    private Timestamp timeIn;

    public Trip(long companyId, long tripId, String airplane, String townFrom,
                String townTo, Timestamp timeOut, Timestamp timeIn) {

        this.companyId = companyId;
        this.tripId = tripId;
        this.airplane = airplane;
        this.townFrom = townFrom;
        this.townTo = townTo;
        this.timeOut = timeOut;
        this.timeIn = timeIn;
    }

    public Trip(){}

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
