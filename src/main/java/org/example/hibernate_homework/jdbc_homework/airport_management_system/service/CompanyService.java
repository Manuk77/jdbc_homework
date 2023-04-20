package org.example.hibernate_homework.jdbc_homework.airport_management_system.service;

import org.example.hibernate_homework.jdbc_homework.airport_management_system.abstract_service.Service;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.Company;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.create_db_tables.CreateAndInsert;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import static org.example.hibernate_homework.jdbc_homework.airport_management_system.create_db_tables.CreateAndInsert.connectToUrl;


public class CompanyService implements Service<Company>{
    Company company;
    Connection con;
    Statement st;
    PreparedStatement pst;
    @Override
    public Company getById(long id) {
        con = connectToUrl();
        CreateAndInsert ci = new CreateAndInsert();
        company = new Company();
        if (validateId(id)) {
            con = connectToUrl();
           try {
               st = con.createStatement();
               ResultSet rs = st.executeQuery("select * from Company where company_id = " + id);
               while (rs.next()) {
                    company.setCompany_id(rs.getLong("company_id"));
                    company.setName(rs.getString("company_name"));
                    company.setFoundDate(rs.getDate("found_date"));
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
            return company;
        }
        throw new IllegalArgumentException("specified id must not be negative");
    }

    @Override
    public Set<Company> getAll() {
        con = connectToUrl();
        Set<Company> comp = new HashSet<>();
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from company");
            while (rs.next()) {
                comp.add(new Company(rs.getLong("company_id"), rs.getString("company_name"),
                        rs.getDate("found_date")));
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
        return comp;
    }

    @Override
    public Set<Company> get(int offset, int perPage, String sort) {
        con = connectToUrl();
        Set<Company> comp = new HashSet<>();
        try{
            st = con.createStatement();
            pst = con.prepareStatement("select * from Company order by " + sort + "limit ?  offset ?");
            pst.setInt(1, perPage);
            pst.setInt(2, offset);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                comp.add(new Company(rs.getLong("company_id"), rs.getString("company_name"),
                        rs.getDate("found_date")));
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
        return comp;
    }

    @Override
    public void save(Company company) {
        con = connectToUrl();
        if (isNotEmpty(company)){
            try {
                st = con.createStatement();
                pst = con.prepareStatement("insert into Company(company_id, namee, found_date) values (?, ?, ?);");
                pst.setLong(1, company.getCompanyId());
                pst.setString(2, company.getName());
                pst.setDate(3, company.getFoundDate());
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
    public void update(Company company, long company_id) {
        con = connectToUrl();
        if (isNotEmpty(company) && validateId(company_id)) {
            try {
                st = con.createStatement();
                pst = con.prepareStatement("update Company set namee = ?, found_date = ? where company_id = ?");
                pst.setString(1, company.getName());
                pst.setDate(2, company.getFoundDate());
                pst.setLong(3, company_id);
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
                pst = con.prepareStatement("select count(company_id) from trip where company_id = ?");
                pst.setLong(1, id);
                ResultSet rs = pst.executeQuery();
                int count = 0;
                if (rs.next())
                    count = rs.getInt("count");
                if (count > 0){
                    System.out.println("can't be removed, first remove company_id from table Trip");
                    return;
                }
                pst = con.prepareStatement("delete from Company where company_id = ?");
                pst.setLong(1, id);
                int row = pst.executeUpdate();
                if (row > 0) {
                    System.out.println("row was deleted successfully");
                } else
                    System.out.println("in table Company isn't row with match company_id");

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
        throw new IllegalArgumentException("Specified id must not be negative");
    }

    private boolean validateId(long id){
        return id > 0;
    }

    private boolean isNotEmpty(Company company){
        return company.getCompanyId() != 0 && company.getName() != null && company.getFoundDate() != null;
    }
}
