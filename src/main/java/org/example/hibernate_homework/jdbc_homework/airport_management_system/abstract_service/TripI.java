package org.example.hibernate_homework.jdbc_homework.airport_management_system.abstract_service;

import org.example.hibernate_homework.jdbc_homework.airport_management_system.Trip;

import java.util.List;

public interface TripI {
    public abstract List<Trip> getTripsFrom(String city);
    public abstract List<Trip> getTripsTo(String city);

}
