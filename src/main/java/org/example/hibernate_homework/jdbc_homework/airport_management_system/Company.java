package org.example.hibernate_homework.jdbc_homework.airport_management_system;

import java.sql.Date;

public class Company {
    private long companyId;
    private String name;
    private Date foundDate;

    public void setCompany_id(long company_id) {
        this.companyId = company_id;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setFoundDate(Date foundDate) {
        this.foundDate = foundDate;
    }

    public Date getFoundDate() {
        return foundDate;
    }

    public Company(long company_id, String name, Date foundDate) {
        this.companyId = company_id;
        this.name = name;
        this.foundDate = foundDate;
    }

    public Company(){}

    private static boolean validString(String str){
        return str != null && !str.isEmpty();
    }

    @Override
    public String toString() {
        return "Company{" +
                "company_id=" + companyId +
                ", name='" + name + '\'' +
                ", foundDate=" + foundDate +
                '}';
    }
}
