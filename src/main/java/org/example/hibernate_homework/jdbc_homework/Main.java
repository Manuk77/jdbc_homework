package org.example.hibernate_homework.jdbc_homework;

import java.sql.SQLException;
import java.util.List;
import static org.example.hibernate_homework.FileReading.read;

public class Main {
    public static void main(String[] args) {
        try{

            AirportManagementSystem.createTableCompany();
            AirportManagementSystem.createTablePassengers();
            AirportManagementSystem.createTablePassInTrip();
            AirportManagementSystem.createTableTrip();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        //Set<Passengers> setPass = new HashSet<>();
        List<String[]> passengers;
        List<String[]> company;
        List<String[]> trip;
        List<String[]> passInTrip;
        //List<Integer> passId;

        final String filePathPas = "/media/manuk/DISK/BDJ java/homework/homework_JDBC/passengers.txt";
        passengers = read(filePathPas);

        final String filePathComp = "/media/manuk/DISK/BDJ java/homework/homework_JDBC/companies.txt";
        company = read(filePathComp);

        final String filePathTrip = "/media/manuk/DISK/BDJ java/homework/homework_JDBC/trip.txt";
        trip = read(filePathTrip);

        final String filePathPassInTrip = "/media/manuk/DISK/BDJ java/homework/homework_JDBC/pass_in_trip.txt";
        passInTrip = read(filePathPassInTrip);

        try {

            AirportManagementSystem.insertIntoCompany(company);
            AirportManagementSystem.insertIntoPassengers(passengers);
            AirportManagementSystem.insertIntoTrip(trip);
            AirportManagementSystem.insertIntoPassInTrip(passInTrip);


        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }




    }
}
