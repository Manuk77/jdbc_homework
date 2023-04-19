package org.example.hibernate_homework.jdbc_homework.airport_management_system.abstract_service;

import org.example.hibernate_homework.jdbc_homework.airport_management_system.Passengers;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.Trip;

import java.util.List;

public interface Passenger {
    public abstract List<? extends Object> getPassengersOfTrip(long tripNumber);
    public abstract void registerTrip(Trip trip, Passengers passengers);
    public abstract void cancelTrip(long passengerId, long tripNumber);

}
