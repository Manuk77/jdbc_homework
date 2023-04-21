package org.example.hibernate_homework.jdbc_homework.homework_hibernate.hibernate_airport_managmente_sistem;

import org.example.hibernate_homework.jdbc_homework.airport_management_system.Passengers;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.Trip;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.abstract_service.Passenger;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.abstract_service.Service;
import org.example.hibernate_homework.jdbc_homework.homework_hibernate.PassengersH;

import java.util.List;
import java.util.Set;

public class PassengersHService implements Service<PassengersH>, Passenger {
    @Override
    public List<? extends Object> getPassengersOfTrip(long tripNumber) {
        return null;
    }

    @Override
    public void registerTrip(Trip trip, Passengers passengers) {

    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) {

    }

    @Override
    public PassengersH getById(long id) {
        return null;
    }

    @Override
    public Set<?> getAll() {
        return null;
    }

    @Override
    public Set<?> get(int offset, int perPage, String sort) {
        return null;
    }

    @Override
    public void save(PassengersH tableName) {

    }

    @Override
    public void update(PassengersH tableName, long id) {

    }

    @Override
    public void delete(long tableNameId) {

    }
}
