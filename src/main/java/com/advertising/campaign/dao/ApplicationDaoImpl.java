package com.advertising.campaign.dao;

import com.advertising.campaign.models.Ad;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Properties;

@Repository
public class ApplicationDaoImpl implements ApplicationDao {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:mem:adcampaings;DB_CLOSE_DELAY=-1";

    private static final String USER = "sa";
    private static final String PASS = "";

    public Ad getCampaingById(Integer id) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Connection conn;
        Statement stmt;

        Class.forName(JDBC_DRIVER).newInstance();
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();
        String sql = "SELECT * FROM ADS WHERE id = " + id;
        ResultSet resultSet = stmt.executeQuery(sql);
//        stmt.close();
//        conn.close();



        Ad ad = new Ad(resultSet.getInt("id"), resultSet.getString("name"),
                resultSet.getInt("status"), ((int[]) resultSet.getArray("platforms").getArray()), resultSet.getString("asset_url"));

        return ad;
    }
}
