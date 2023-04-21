package org.example.hibernate_homework.jdbc_homework.homework_hibernate.hibernate_airport_managmente_sistem;

import org.example.hibernate_homework.jdbc_homework.airport_management_system.abstract_service.Service;
import org.example.hibernate_homework.jdbc_homework.homework_hibernate.CompanyH;

import java.util.Set;

public class CompanyHService implements Service<CompanyH>{

    @Override
    public CompanyH getById(long id) {
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
    public void save(CompanyH tableName) {

    }

    @Override
    public void update(CompanyH tableName, long id) {

    }

    @Override
    public void delete(long tableNameId) {

    }
}
