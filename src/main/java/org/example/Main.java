package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
       Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/classWork", "postgres", "manukpoloz2000");
       Statement st = con.createStatement();

       boolean b = st.execute("create table temp(name varchar(50) not null, age int not null)");
        int i = st.executeUpdate("insert into temp(name, age) values ('manuk', 23)");
            i = st.executeUpdate("insert into temp(name, age) values ('perj', 22)");

        st.close();
        con.close();
    }
}