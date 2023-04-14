package org.example.hibernate_homework.jdbc_homework;

import java.sql.*;
import java.util.List;

public class AirportManagementSystem {
    public static void createTableCompany() throws SQLException {
        final String comName =
                "create table Company(company_name varchar(50) not null , found_date varchar(50) not null)";

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JDBC", "postgres", "manukpoloz2000");
        Statement st = con.createStatement();
        st.execute(comName);

        st.close();
        con.close();

    }

    public static void createTablePassengers() throws SQLException {
        final String passName =
                "create table Passengers(passenger_id integer primary key, namee varchar(50) not null, phone varchar(50) not null, " +
                        "country varchar(50) not null, city varchar(50) not null";


        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JDBC", "postgres", "manukpoloz2000");
        Statement st = con.createStatement();
        st.execute(passName);

        st.close();
        con.close();
    }

    public static void createTableTrip() throws SQLException {
        final String tripName = "create table Trip(trip_id integer primary key, passenger_id integer references Passengers(passenger_id)," +
                "company varchar(50) not null, town_from varchar(50) not null , town_to varchar(50)not null ," +
                "time_out date not null, time_in date not null )";

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JDBC", "postgres", "manukpoloz2000");
        Statement st = con.createStatement();
        st.execute(tripName);
        st.close();
        con.close();

    }

    public static void createTablePassInTrip() throws SQLException {
        final String passInTrip = "create table Pass_in_trip(trip_id integer references Trip(trip_id), " +
                "passenger_id integer references Passengers(passenger_id), date date not null, place varchar(50) not null )";
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JDBC", "postgres", "manukpoloz2000");
        Statement st = con.createStatement();
        st.execute(passInTrip);

        st.close();
        con.close();
    }

    public static void insertIntoCompany(List<String[]> company) throws SQLException{
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JDBC", "postgres", "manukpoloz2000");

        for(int i = 0; i < company.size(); ++i) {
            final String insert = "insert into Company(company_name, found_date) values (?, ?)";

            PreparedStatement pstm = con.prepareStatement(insert);
            for(int j = 0; j < 2; ++j) {
                pstm.setString(j + 1, company.get(i)[j]);

            }
            pstm.executeUpdate();
        }


        con.close();



    }

    public static void insertIntoPassengers(List<String[]> passenger) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JDBC", "postgres", "manukpoloz2000");

        for(int i = 0; i < passenger.size(); ++i) {
            final String insert = "insert into Passengers(passenger_id, namee, phone, country, city) values (?,?,?,?,?)";

            PreparedStatement pstm = con.prepareStatement(insert);
            for(int j = 0; j < 5; ++j) {
                pstm.setString(j + 1, passenger.get(i)[j]);

            }
            pstm.executeUpdate();
        }
        con.close();
    }

    public static void insertIntoTrip(List<String[]> trip) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JDBC", "postgres", "manukpoloz2000");
        for(int i = 0; i < trip.size(); ++i){
            final String insert = "insert into Trip(trip_id, passenger_id, company, town_from, town_to, time_out, time_to) values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstm = con.prepareStatement(insert);
            for (int j = 0; j < 7; ++j) {
                pstm.setString(j + 1, trip.get(i)[j]);
            }
            pstm.executeUpdate();

        }
        con.close();



    }

    public static void insertIntoPassInTrip(List<String[]> passInTrip) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JDBC", "postgres", "manukpoloz2000");

        for (int i = 0; i < passInTrip.size(); ++i){
            final String insert = "insert into Pass_in_trip(trip_id, passenger_id, date, place) values (?, ?, ?, ?)";
            PreparedStatement pstm = con.prepareStatement(insert);
            for (int j = 0; j < 4; ++j) {
                pstm.setString(j + 1, passInTrip.get(i)[j]);

            }
            pstm.executeUpdate();
        }
        con.close();

    }

    private static boolean validString(String str){
        return str != null && !str.isEmpty();
    }
}
