package com.advertising.campaign.dao;

import com.advertising.campaign.models.Ad;
import com.advertising.campaign.models.Campaing;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class ApplicationDaoImpl implements ApplicationDao {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:mem:adcampaings;DB_CLOSE_DELAY=-1";

    private static final String USER = "sa";
    private static final String PASS = "";

    public Ad getAdById(Integer id) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Connection conn;
        Statement stmt;

        Class.forName(JDBC_DRIVER).newInstance();
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();
        String sql = "SELECT * FROM ADS WHERE id = " + id;
        ResultSet resultSet = stmt.executeQuery(sql);

        Array platforms = resultSet.getArray("platforms");
        int[] arrayPlatforms = (int[]) platforms.getArray();

        return new Ad(resultSet.getInt("id"), resultSet.getString("name"),
                resultSet.getInt("status"), arrayPlatforms, resultSet.getString("asset_url"));
    }

    @Override
    public Campaing getCampaingById(Integer id) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Connection conn;
        Statement stmt;

        Class.forName(JDBC_DRIVER).newInstance();
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();
        String sql = "SELECT * FROM CAMPAINGS WHERE id = " + id;
        ResultSet resultSet = stmt.executeQuery(sql);

        Array ads = resultSet.getArray("ads");
        int[] arrayAds = (int[])ads.getArray();

        return new Campaing(resultSet.getInt("id"), resultSet.getString("name"),
                resultSet.getInt("status"), resultSet.getTimestamp("start_date"), resultSet.getTimestamp("end_date"), arrayAds);
    }

    @Override
    public void deleteAdById(Integer id) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        Connection conn;
        Statement stmt;

        Class.forName(JDBC_DRIVER).newInstance();
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();
        String sql = "DELETE FROM ADS WHERE id = " + id;
        stmt.execute(sql);
    }

    @Override
    public void deleteCampaignById(Integer id) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Connection conn;
        Statement stmt;

        Class.forName(JDBC_DRIVER).newInstance();
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();
        String sql = "DELETE FROM CAMPAINGS WHERE id = " + id;
        stmt.execute(sql);
    }
}
