package org.example.hibernate_homework.jdbc_homework.airport_management_system.abstract_service;


import java.util.Set;

public interface Service<T> {
   public abstract T getById(long id);
   public abstract Set<? extends Object> getAll();
   public abstract Set<? extends Object> get(int offset, int perPage, String sort);
   public abstract void save(T tableName);
   public abstract void update(T tableName, long id);
   public abstract void delete(long tableNameId);

}
