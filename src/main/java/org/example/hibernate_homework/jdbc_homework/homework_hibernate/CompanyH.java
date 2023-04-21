package org.example.hibernate_homework.jdbc_homework.homework_hibernate;

import javax.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
public class CompanyH {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long companyId;
    @Column(name = "company_name", nullable = false, length = 50)
    private String name;
    @Column(name = "found_date", nullable = false)
    private Date foundDate;

    public void setCompanyId(long company_id) {
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

    public CompanyH(long company_id, String name, Date foundDate) {
        this.companyId = company_id;
        this.name = name;
        this.foundDate = foundDate;
    }

    public CompanyH(){}

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
