package org.example.hibernate_homework.jdbc_homework.homework_hibernate.hibernate_airport_managmente_sistem;

import org.example.hibernate_homework.jdbc_homework.airport_management_system.Trip;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.abstract_service.Service;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.abstract_service.TripI;
import org.example.hibernate_homework.jdbc_homework.homework_hibernate.TripH;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class TripHService implements Service<TripH>, TripI {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    @Override
    public TripH getById(long id) {
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
    public void save(TripH tableName) {

    }

    @Override
    public void update(TripH tableName, long id) {

    }

    @Override
    public void delete(long tableNameId) {

    }

    @Override
    public List<Trip> getTripsFrom(String city) {
        return null;
    }

    @Override
    public List<Trip> getTripsTo(String city) {
        return null;
    }
}
