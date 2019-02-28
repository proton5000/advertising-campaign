package com.advertising.campaign.dao;

import com.advertising.campaign.models.Ad;
import com.advertising.campaign.models.Campaing;
import com.advertising.campaign.models.request.AdCreate;
import com.advertising.campaign.models.request.CampaingCreate;
import com.advertising.campaign.models.response.CampaingMiniResponse;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ApplicationDaoImpl implements ApplicationDao {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:mem:adcampaings;DB_CLOSE_DELAY=-1";

    private static final String USER = "sa";
    private static final String PASS = "";

    private Statement getStatement() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Connection conn;
        Class.forName(JDBC_DRIVER).newInstance();
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        return conn.createStatement();
    }

    @Override
    public Ad getAdById(Integer id) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        String sql = "SELECT * FROM ADS WHERE ID = " + id;
        ResultSet resultSet = getStatement().executeQuery(sql);
        resultSet.next();
//        Array platforms = resultSet.getArray("PLATFORMS");
//        int[] arrayPlatforms = (int[]) platforms.getArray();

        return new Ad(resultSet.getInt("ID"), resultSet.getString("NAME"),
                resultSet.getInt("STATUS"), null, resultSet.getString("ASSERT_URL"));
    }

    @Override
    public Campaing getCampaingById(Integer id) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        String sql = "SELECT * FROM CAMPAINGS WHERE ID = " + id;
        ResultSet resultSet = getStatement().executeQuery(sql);
        resultSet.next();
//        Array ads = resultSet.getArray("ADS");
//        int[] arrayAds = (int[])ads.getArray();

        return new Campaing(resultSet.getInt("ID"), resultSet.getString("NAME"),
                resultSet.getInt("STATUS"), resultSet.getTimestamp("START_DATE"), resultSet.getTimestamp("END_DATE"), null);
    }

    @Override
    public void deleteAdById(Integer id) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        String sql = "DELETE FROM ADS WHERE ID = " + id;
        getStatement().execute(sql);
    }

    @Override
    public void deleteCampaignById(Integer id) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        String sql = "DELETE FROM CAMPAINGS WHERE ID = " + id;
        getStatement().execute(sql);
    }

    @Override
    public List<CampaingMiniResponse> getSummaries(String orderBy, Integer skip) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        String sql = "SELECT ID, NAME, STATUS, ADS FROM CAMPAINGS";

        if (!orderBy.isEmpty() && orderBy.trim().length() != 0) {
            sql += " ORDER BY " + orderBy;
        }

        sql += " WHERE ROWNUM BETWEEN "+ skip +" AND " + skip + 5;

        ResultSet resultSet = getStatement().executeQuery(sql);

        List<CampaingMiniResponse> campaingMiniResponseList = new ArrayList<>();

        while (resultSet.next()) {

            Array ads = resultSet.getArray("ADS");
            Ad[] adsArray = (Ad[])ads.getArray();

            CampaingMiniResponse campaingMiniResponse =
                    new CampaingMiniResponse(resultSet.getInt("ID"),
                            resultSet.getString("NAME"), resultSet.getInt("STATUS"), adsArray.length);

            campaingMiniResponseList.add(campaingMiniResponse);
        }

        return campaingMiniResponseList;
    }

    @Override
    public Campaing createCampaign(CampaingCreate campaingCreate) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        String sql = "INSERT INTO CAMPAINGS(NAME, STATUS, START_DATE, END_DATE, ADS) " +
                "VALUES("+campaingCreate.getName()+", "+campaingCreate.getStatus()+", " +
                ""+campaingCreate.getStart_date()+", "+campaingCreate.getEnd_date()+", "+ Arrays.toString(campaingCreate.getAds()) +")";
        ResultSet resultSet = getStatement().executeQuery(sql);

        return new Campaing(resultSet.getInt("ID"), resultSet.getString("NAME"),
                resultSet.getInt("STATUS"), resultSet.getTimestamp("START_DATE"),
                resultSet.getTimestamp("END_DATE"), null);
    }

    @Override
    public Campaing updateCampaignById(Integer id, CampaingCreate campaingCreate) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        String sql = "INSERT INTO CAMPAINGS(NAME, STATUS, START_DATE, END_DATE, ADS) WHERE ID = "+ id +
                " VALUES(" + campaingCreate.getName() + ", " + campaingCreate.getStatus() + ", " +
                "" + campaingCreate.getStart_date() + ", " + campaingCreate.getEnd_date() + ", " + Arrays.toString(campaingCreate.getAds()) + ")";
        ResultSet resultSet = getStatement().executeQuery(sql);

        Array ads = resultSet.getArray("ADS");
        Ad[] adsArray = (Ad[])ads.getArray();

        int[] adsIds = new int[adsArray.length];

        for (int i = 0; i < adsArray.length; i++) {
            adsIds[i] = adsArray[i].getId();
        }

        return new Campaing(resultSet.getInt("ID"), resultSet.getString("NAME"),
                resultSet.getInt("STATUS"), resultSet.getTimestamp("START_DATE"),
                resultSet.getTimestamp("END_DATE"), adsIds);
    }

    @Override
    public Ad updateAdById(Integer id, AdCreate adCreate) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        String sql = "INSERT INTO ADS(NAME, STATUS, PLATFORMS, ASSERT_URL) " +
                "VALUES(" + adCreate.getName() + ", " + adCreate.getStatus() + ", " +
                "" + Arrays.toString(adCreate.getPlatforms()) + ", " +
                "" + adCreate.getAsset_url() + ")";

        ResultSet resultSet = getStatement().executeQuery(sql);

        Array platforms = resultSet.getArray("PLATFORMS");
        int[] platformsArray = (int[])platforms.getArray();

        return new Ad(resultSet.getInt("ID"), resultSet.getString("NAME"),
                resultSet.getInt("STATUS"), platformsArray,
                resultSet.getString("ASSERT_URL"));
    }
}
