package org.example.hibernate_homework.jdbc_homework.airport_management_system.service;

import org.example.hibernate_homework.jdbc_homework.airport_management_system.abstract_service.Service;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.abstract_service.TripI;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.Trip;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.create_db_tables.CreateAndInsert;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.example.hibernate_homework.jdbc_homework.airport_management_system.create_db_tables.CreateAndInsert.connectToUrl;

public class TripService implements Service<Trip>, TripI {
    Trip trip;
    Connection con;
    Statement st;
    PreparedStatement pst;

    @Override
    public Trip getById(long id) {
        con = connectToUrl();
        CreateAndInsert ci = new CreateAndInsert();
        trip = new Trip();
        if (validateId(id)) {
            con = connectToUrl();
            try {
                st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from Company where company_id = " + id);
                while (rs.next()) {
                    trip.setCompanyId(rs.getLong("company_id"));
                    trip.setTripId(rs.getLong("trip_id"));
                    trip.setAirplane(rs.getString("airplane"));
                    trip.setTownFrom(rs.getString("town_from"));
                    trip.setTownTo(rs.getString("town_to"));
                    trip.setTimeOut(rs.getTimestamp("time_out"));
                    trip.setTimeIn(rs.getTimestamp("time_in"));
                }

            }catch (SQLException e){
                System.out.println(e.getMessage());
            }finally {
                trip = null;
                try {
                    if (st != null)
                        st.close();
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
            return trip;
        }
        throw new IllegalArgumentException("specified id must not be negative");
    }

    @Override
    public Set<Trip> getAll() {
        con = connectToUrl();
        Set<Trip> trip = new HashSet<>();
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Trip");
            while (rs.next()) {
                trip.add(new Trip(rs.getLong("company_id"), rs.getLong("trip_id"),
                        rs.getString("airplane"), rs.getString("town_from"), rs.getString("town_to"),
                        rs.getTimestamp("time_out"), rs.getTimestamp("time_in")));
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
        return trip;
    }

    public Set<Trip> get(int offset, int perPage, String sort) {
        con = connectToUrl();
        Set<Trip> trip = new HashSet<>();
        try{
            st = con.createStatement();
            pst = con.prepareStatement("select * from Trip order by " + sort + "limit ?  offset ?");
            pst.setInt(1, perPage);
            pst.setInt(2, offset);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                trip.add(new Trip(rs.getLong("company_id"), rs.getLong("trip_id"),
                        rs.getString("airplane"), rs.getString("town_from"), rs.getString("town_to"),
                        rs.getTimestamp("time_out"), rs.getTimestamp("time_in")));
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
        return trip;
    }

    @Override
    public void save(Trip trip) {
        con = connectToUrl();
        if (isNotEmpty(trip)){
            try {
                st = con.createStatement();
                pst = con.prepareStatement("insert into Trip(company_id, trip_id, airplane, town_from, town_to," +
                        "time_out, time_in) values (?, ?, ?, ?, ?, ?, ?);");
                pst.setLong(1, trip.getCompanyId());
                pst.setLong(2, trip.getTripId());
                pst.setString(3, trip.getAirplane());
                pst.setString(4, trip.getTownFrom());
                pst.setString(5, trip.getTownTo());
                pst.setTimestamp(6, trip.getTimeOut());
                pst.setTimestamp(7, trip.getTimeIn());
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
    public void update(Trip trip, long trip_id) {
        con = connectToUrl();
        if (isNotEmpty(trip) && validateId(trip_id)) {
            try {
                st = con.createStatement();
                pst = con.prepareStatement("update Trip set company_id = ?, airplane = ?, " +
                        "town_from = ?, town_to = ?, time_out = ?, time_ in = ? where trip_id = ?");
                pst.setLong(1, trip.getCompanyId());
                pst.setString(2, trip.getAirplane());
                pst.setString(3, trip.getTownFrom());
                pst.setString(4, trip.getTownTo());
                pst.setTimestamp(5, trip.getTimeOut());
                pst.setTimestamp(6, trip.getTimeIn());
                pst.setLong(7, trip_id);
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

        }

    }

    @Override
    public void delete(long id) {
        con = connectToUrl();
        if (validateId(id)){
            try {
                pst = con.prepareStatement("delete from Trip where trip_id = ?");
                pst.setLong(1, id);
                int row = pst.executeUpdate();
                if (row > 0) {
                    System.out.println("row was deleted successfully");
                } else
                    System.out.println("in table Trip isn't row with match trip_id");

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
        throw new IllegalArgumentException("specified index must not be negative");
    }

    @Override
    public List<Trip> getTripsFrom(String city) {
        con = connectToUrl();
        if (validString(city)) {
            List<Trip> trip = new ArrayList<>();
            try {
                st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from Trip where town_from = ?");
                while (rs.next()) {
                    trip.add(new Trip(rs.getLong("company_id"), rs.getLong("trip_id"),
                            rs.getString("airplane"), rs.getString("town_from"), rs.getString("town_to"),
                            rs.getTimestamp("time_out"), rs.getTimestamp("time_in")));
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
            return trip;
        }
        throw new IllegalArgumentException("specified city must not be null or empty");
    }

    @Override
    public List<Trip> getTripsTo(String city) {
        con = connectToUrl();
        if (validString(city)) {
            List<Trip> trip = new ArrayList<>();
            try {
                st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from Trip where town_to = ?");
                while (rs.next()) {
                    trip.add(new Trip(rs.getLong("company_id"), rs.getLong("trip_id"),
                            rs.getString("airplane"), rs.getString("town_from"), rs.getString("town_to"),
                            rs.getTimestamp("time_out"), rs.getTimestamp("time_in")));
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
            return trip;
        }
        throw new IllegalArgumentException("specified city must not be null or empty");
    }



    private boolean validateId(long id){
        return id > 0;
    }

    private boolean isNotEmpty(Trip trip){
        return trip.getTripId() != 0 && trip.getCompanyId() != 0 && trip.getAirplane() != null &&
                trip.getTownFrom() != null && trip.getTownTo() != null && trip.getTimeOut() != null && trip.getTimeIn() != null;
    }

    private boolean validString(String str){
        return str != null && !str.isEmpty();
    }
}
