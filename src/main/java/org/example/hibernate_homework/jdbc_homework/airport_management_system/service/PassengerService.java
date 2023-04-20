package org.example.hibernate_homework.jdbc_homework.airport_management_system.service;

import org.example.hibernate_homework.jdbc_homework.airport_management_system.abstract_service.Passenger;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.abstract_service.Service;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.Passengers;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.Trip;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.create_db_tables.CreateAndInsert;

import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.example.hibernate_homework.jdbc_homework.airport_management_system.create_db_tables.CreateAndInsert.connectToUrl;

public class PassengerService implements Service<Passengers>, Passenger {
    Passengers passengers;
    Connection con;
    Statement st;
    PreparedStatement pst;


    @Override
    public Passengers getById(long id) {
        con = connectToUrl();
        CreateAndInsert ci = new CreateAndInsert();
        passengers = new Passengers();
        if (validateId(id)) {
            con = connectToUrl();
            try {
                st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from Passengers where passenger_id = " + id);
                while (rs.next()) {
                   passengers.setPassenger_id(rs.getLong( "passenger_id"));
                   passengers.setName(rs.getString("namee"));
                   passengers.setPhone(rs.getString("phone"));
                   passengers.setCountry(rs.getString("country"));
                   passengers.setCity(rs.getString("city"));
                }

            }catch (SQLException e){
                System.out.println(e.getMessage());
            }finally {
                try {
                    if (st != null)
                        st.close();
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
            return passengers;
        }
        throw new IllegalArgumentException("specified id must not be negative");
    }


    @Override
    public Set<Passengers> getAll() {
        con = connectToUrl();
        Set<Passengers> passenger = new HashSet<>();
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Passengers");
            while (rs.next()) {
               passenger.add(new Passengers(rs.getLong("passenger_id"), rs.getString("namee"),
                       rs.getString("phone"), rs.getString("country"), rs.getString("city")));
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            if (st != null)
                try {
                    st.close();
                }catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
        }
        return passenger;
    }

    public Set<Passengers> get(int offset, int perPage, String sort) {
        con = connectToUrl();
        Set<Passengers> passenger = new HashSet<>();
        try{
            st = con.createStatement();
            pst = con.prepareStatement("select * from Passengers order by ? limit ?  offset ?");
            pst.setString(1, sort);
            pst.setInt(2, perPage);
            pst.setInt(3, offset);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                passenger.add(new Passengers(rs.getLong("passenger_id"), rs.getString("namee"),
                        rs.getString("phone"), rs.getString("country"), rs.getString("city")));
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            if (st != null )
                try {
                    st.close();
                    if (pst != null)
                        pst.close();
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
        }
        return passenger;
    }

    @Override
    public void save(Passengers passengers) {
        con = connectToUrl();
        if (isNotEmpty(passengers)){
            try {
                st = con.createStatement();
                pst = con.prepareStatement("insert into Passegers(passenger_id, namee, phoen, country, city)" +
                        ") values (?, ?, ?, ?, ?);");
                pst.setLong(1, passengers.getPassenger_id());
                pst.setString(2, passengers.getName());
                pst.setString(3, passengers.getPhone());
                pst.setString(4, passengers.getCountry());
                pst.setString(5, passengers.getCity());
                pst.executeUpdate();

            }catch (SQLException e){
                System.out.println(e.getMessage());
            }finally {
                if (st != null)
                    try {
                        st.close();
                        if (pst != null)
                            pst.close();
                    }catch (SQLException e){
                        System.out.println(e.getMessage());
                    }
            }
        }
    }

    @Override
    public void update(Passengers passengers, long passenger_id) {
        con = connectToUrl();
        if (isNotEmpty(passengers) && validateId(passenger_id)) {
            try {
                st = con.createStatement();
                pst = con.prepareStatement("update Passengers set  namee = ?, phone = ?, " +
                        "country = ?, city = ? where passenger_id = ?");
                pst.setString(1, passengers.getName());
                pst.setString(2, passengers.getPhone());
                pst.setString(3, passengers.getCountry());
                pst.setString(4, passengers.getCity());
                pst.setLong(5, passenger_id);
                pst.executeUpdate();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }finally {
                if (st != null)
                    try {
                        st.close();
                        if (pst != null)
                            pst.close();
                    }catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
            }
            return;
        }
        throw new IllegalArgumentException("specified object must not be empty ot null, or id must not be negative");
    }

    @Override
    public void delete(long id) {
        con = connectToUrl();
        if (validateId(id)){
            try {
                pst = con.prepareStatement("delete from Passengers where passenger_id = ?");
                pst.setLong(1, id);
                int row = pst.executeUpdate();
                if (row > 0) {
                    System.out.println("row was deleted successfully");
                } else
                    System.out.println("in table Passengers isn't row with match passenger_id");

            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }finally {
                if (st != null)
                    try {
                        st.close();
                        if (pst != null)
                            pst.close();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
            }
            return;
        }
        throw new IllegalArgumentException("specified id must not be negative");
    }

    @Override
    public List<? extends Object> getPassengersOfTrip(long tripNumber) {
        return null;
    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) {

    }

    @Override
    public void registerTrip(Trip trip, Passengers passengers) {

    }

    private boolean validateId(long id){
        return id > 0;
    }

    private boolean isNotEmpty(Passengers passengers){
        return passengers.getPassenger_id() != 0 && passengers.getName() != null && passengers.getPhone() != null &&
                passengers.getCountry() != null && passengers.getCity() != null;    }

    private boolean validString(String str){
        return str != null && !str.isEmpty();
    }
}
