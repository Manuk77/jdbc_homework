package org.example.hibernate_homework.jdbc_homework.airport_management_system.create_db_tables;

import org.example.hibernate_homework.jdbc_homework.airport_management_system.Company;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.Passengers;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.Trip;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.service.CompanyService;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.service.PassengerService;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.service.TripService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;


public class CreateAndInsert {
    private String url = "jdbc:postgresql://localhost:5432/JDBC";
    private String userName = "postgres";
    private String passwd = "manukpoloz";
    Connection con;
    Statement st;
    Scanner sc;
    CompanyService compService;
    TripService tripService;
    PassengerService passService;

    public void connectToUrl() {
        try {
            if (con == null)
                con = DriverManager.getConnection(url, userName,passwd);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void closeConnection() {
        con = null;
    }


    private void insertCompany(String filePath){
        if (validString(filePath)) {
            connection();
            createStatement();
            sc = new Scanner(filePath);
            String[] comp;
            try {

                while (sc.hasNext()) {
                    StringBuilder sb = new StringBuilder("insert ino Company(namee, found_date) values (");
                    comp = sc.nextLine().split(",");
                    sb.append("'").append(comp[0] + "','").append(comp[1] + "');");
                    st.executeUpdate(sb.toString());
                    sb.setLength(0);

                }

            }catch (SQLException e ){
                System.out.println(e.getMessage());
            }finally {
                sc.close();
                if (st != null)
                    try {
                        st.close();
                    }catch (SQLException e){
                        System.out.println(e.getMessage());
                    }

            }
        }
        throw new IllegalArgumentException("filePath must not be null or empty");

    }

    private void insertPassengers(String filePath){
        if (validString(filePath)) {
            createStatement();
            sc = new Scanner(filePath);
            String[] passenger;
            Random random = new Random();
            try {
                while (sc.hasNext()){
                    int i = 1;
                    StringBuilder sb = new StringBuilder("insert into Passengers(passenger_id, namee, phone, country, city) values (");
                    passenger = sc.nextLine().split(",");
                    sb.append((random.nextInt(37) + 1) + "'").append(passenger[0] +"','")
                            .append(passenger[1]+"','" + passenger[2] +"'," + "'" + passenger[3] +"','" + passenger[4]+"');");
                    st.executeUpdate(sb.toString());
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }finally {
                sc.close();
                if (st != null)
                    try {
                        st.close();
                    }catch (SQLException e){
                        System.out.println(e.getMessage());
                    }
            }
        }
        throw new IllegalArgumentException("filepath must not be null or empty");
    }

    private void insertTrip(String filePath){
        if (validString(filePath)) {
            createStatement();
            sc = new Scanner(filePath);
            createStatement();
            StringBuilder sb = new StringBuilder("insert into Trip(trip_id, passenger_id," +
                    " company, town_from, town_to, time_out, time_in) values(");
            String[] trip;
            try{
                while (sc.hasNext()){
                    int i = 1;
                       trip = sc.nextLine().split(",");
                       sb.append(parserToInt(trip[0]) + "," + parserToInt(trip[1]) + ",'" + trip[2] +
                               "','" + trip[3] + "','" + trip[4] +"','" + trip[5] + "','" + trip[6] + "');");
                       st.executeUpdate(sb.toString());
                }
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }finally {
                sc.close();
                if (st != null)
                    try {
                        st.close();
                    }catch (SQLException e){
                        System.out.println(e.getMessage());
                    }
            }
        }
    }

    private void insertPassInTrip(String filePath) {
        if (validString(filePath)) {
            createStatement();
            sc = new Scanner(filePath);
            String[] passInT;
            StringBuilder sb = new StringBuilder("insert into Pass_in_trip(trip_id, passenger_id, datee, place) values(");
            try {
                while (sc.hasNext()) {
                    passInT = sc.nextLine().split(",");
                    sb.append(parserToInt(passInT[0]) + "," + parserToInt(passInT[1] + ",'" + passInT[2] + "','" +
                            passInT[3] + "');"));
                    st.executeUpdate(sb.toString());

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

        }
    }






    private void createTableCompany() {
        final String comName =
                "create table Company(company_name varchar(50) not null , found_date varchar(50) not null);";

        try{
            connectToUrl();
            Statement st = con.createStatement();
            st.execute(comName);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            if (st != null)
                try {
                    st.close();
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
        }


    }

    private void createTablePassengers() throws SQLException {
        final String passName =
                "create table Passengers(passenger_id integer primary key, namee varchar(50) not null," +
                        " phone varchar(50) not null, country varchar(50) not null, city varchar(50) not null);";
        Statement st = con.createStatement();
        st.execute(passName);

        st.close();

    }

    private void createTableTrip() throws SQLException {
        final String tripName = "create table Trip(trip_id integer primary key, passenger_id integer references Passengers(passenger_id)," +
                " company varchar(50) not null, town_from varchar(50) not null ," +
                " town_to varchar(50)not null, time_out date not null, time_in date not null );";

        Statement st = con.createStatement();
        st.execute(tripName);
        st.close();

    }

    private void createTablePassInTrip() throws SQLException {
        final String passInTrip = "create table Pass_in_trip(trip_id integer references Trip(trip_id), " +
                "passenger_id integer references Passengers(passenger_id), datee date not null, place varchar(50) not null )";
        Statement st = con.createStatement();
        st.execute(passInTrip);

        st.close();
        con.close();
    }

    public void createAllTables(){
        try{
            createTableCompany();
            createTablePassengers();
            createTableTrip();
            createTablePassInTrip();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
    public void insertAllTables(String[] paths) {
        if (paths.length == 4) {
            insertCompany(paths[0]);
            insertPassengers(paths[1]);
            insertTrip(paths[2]);
            insertPassInTrip(paths[3]);
        }
        throw new IllegalArgumentException("specified array of String must not be null or less or more than 4 elements");

    }

    private Set<Company> readFromCompany(int choice) {
        compService = new CompanyService();
        Set<Company> comp = new HashSet<>();
        sc = new Scanner(System.in);
        Scanner scStr = new Scanner(System.in);

        if (choice == 1){
            System.out.print("enter id for searching -> ");
            int id = sc.nextInt();
            comp.add(compService.getById(id));

        } else if (choice == 2) {
            comp = compService.getAll();

        } else if (choice == 3) {
            System.out.print("enter offset -> ");
            int offset = sc.nextInt();
            System.out.println("enter perPage -> ");
            int perPage = sc.nextInt();
            System.out.print("enter sort -> ");
            String sort = scStr.toString();
            comp = compService.get(offset, perPage, sort);
        }
        return comp;
    }
    private Set<Passengers> readFromPassenger(int choice) {
        passService = new PassengerService();
        Set<Passengers> pass = new HashSet<>();
        sc = new Scanner(System.in);
        Scanner scStr = new Scanner(System.in);

        if (choice == 1){
            System.out.print("enter id for searching -> ");
            int id = sc.nextInt();
            pass.add(passService.getById(id));

        } else if (choice == 2) {
            pass = passService.getAll();

        } else if (choice == 3) {
            System.out.print("enter offset -> ");
            int offset = sc.nextInt();
            System.out.println("enter perPage -> ");
            int perPage = sc.nextInt();
            System.out.print("enter sort -> ");
            String sort = scStr.toString();
            pass = passService.get(offset, perPage, sort);
        }
        return pass;
    }

    private Set<Trip> readFromTrip(int choice) {
        tripService = new TripService();
        Set<Trip> trip = new HashSet<>();
        sc = new Scanner(System.in);
        Scanner scStr = new Scanner(System.in);
        if (choice == 1){
            System.out.print("enter id for searching -> ");
            int id = sc.nextInt();
            trip.add(tripService.getById(id));
        } else if (choice == 2) {
            trip = tripService.getAll();
        } else if (choice == 3) {
            System.out.print("enter offset -> ");
            int offset = sc.nextInt();
            System.out.println("enter perPage -> ");
            int perPage = sc.nextInt();
            System.out.print("enter sort -> ");
            String sort = scStr.toString();
            trip = tripService.get(offset, perPage, sort);
        }
        return trip;
    }

    public void read(int choice) {
        if (choice == 1) {
            sc = new Scanner(System.in);
            menuComp();
            int choiceCompany = sc.nextInt();
            sc = null;
            for(Company com: readFromCompany(choiceCompany)) {
                System.out.println(com);
            }
        }else if (choice == 2) {
            sc = new Scanner(System.in);
            menuPass();
            int choicePassenger = sc.nextInt();
            sc = null;
            for(Passengers pas :readFromPassenger(choicePassenger)) {
                System.out.println(pas);
            }
        }else {
            sc = new Scanner(System.in);
            menuTrip();
            int choiceTrip = sc.nextInt();
            sc = null;
            for(Trip trip :readFromTrip(choiceTrip)) {
                System.out.println(trip);
            }
        }
    }

    public void deleteFrom(int choice) {
        if (choice == 1) {
            System.out.print("Enter id for deleting -> ");
            long id = sc.nextLong();
            compService.delete(id);
        }else if (choice == 2) {
            System.out.print("Enter id for deleting -> ");
            long id = sc.nextLong();
            passService.delete(id);
        }else {
            System.out.println("Enter id for deleting -> ");
            long id = sc.nextLong();
            tripService.delete(id);
        }
    }



    private void menuComp() {
        System.out.println("1. Company getById(long id)");
        System.out.println("2. Set<Company> getAll()");
        System.out.println("3. Set<Company> get(int offset, int perPage, String sort)");
    }

    private void menuPass() {
        System.out.println("1. Passenger getById(long id)");
        System.out.println("2. Set<Passenger> getAll()");
        System.out.println("3. Set<Passenger> get(int offset, int perPage, String sort)");
    }

    private void menuTrip() {
        System.out.println("1. Trip getById(long id)");
        System.out.println("2.  Set<Trip> getAll()");
        System.out.println("3.  Set<Trip> get(int offset, int perPage, String sort)");
    }








    private void connection(){
        try {
           con = DriverManager.getConnection(url, userName, passwd);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Connection getCon() {
        return con;
    }

    private void createStatement(){
        try {
            st = con.createStatement();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static int parserToInt(String trip_id) {
        int id = -1;
        if (isDigit(trip_id))
           id = parser(trip_id);
        return id;
    }
    private static boolean isDigit(String str) {
        for (int i = 0; i < str.length(); ++i){
            if (!Character.isDigit(str.charAt(i)))
                return false;
        }
        return true;
    }

    private static int parser(String str) {
        int number = Integer.parseInt(str);
        return number;
    }



    private static boolean validString(String str){
        return str != null && !str.isEmpty();
    }
}
