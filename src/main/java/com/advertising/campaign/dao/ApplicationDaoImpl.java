package com.advertising.campaign.dao;

import com.advertising.campaign.models.Campaing;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
@Transactional
public class ApplicationDaoImpl implements ApplicationDao {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/adcampaings";

    static final String USER = "sa";
    static final String PASS = "";

    public void createCampaingTable() {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER).newInstance();
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "CREATE TABLE CAMPAINGS " +
                    "(id INTEGER NOT NULL, " +
                    " name VARCHAR(255) NOT NULL, " +
                    " status INTEGER NOT NULL, " +
                    " start_date TIMESTAMP NOT NULL, " +
                    " end_date TIMESTAMP NOT NULL, " +
                    " [ads] INTEGER NOT NULL, " +
                    " PRIMARY KEY (id))";
            System.out.println("sql = " + sql);
            stmt.execute(sql);
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void createAdTable() {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER).newInstance();
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "CREATE TABLE ADS " +
                    "(id INTEGER NOT NULL, " +
                    " name VARCHAR(255) NOT NULL, " +
                    " status INTEGER NOT NULL, " +
                    " [platforms] INTEGER NOT NULL, " +
                    " assert_url VARCHAR(255) NOT NULL, " +
                    " PRIMARY KEY (id))";
            System.out.println("sql = " + sql);
            stmt.execute(sql);
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
