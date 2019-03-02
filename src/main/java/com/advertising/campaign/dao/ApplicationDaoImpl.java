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

    private Connection getConnection() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Class.forName(JDBC_DRIVER).newInstance();
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private int[] getIntArray(java.sql.Array arr) throws SQLException {

        Object[] sqlArray = (Object[])arr.getArray();
        int[] arrInt = new int[sqlArray.length];

        for (int i = 0; i < sqlArray.length; i++) {
            arrInt[i] = Integer.parseInt(sqlArray[i].toString());
        }

        return arrInt;
    }

    private Integer getCountOfAd(Integer campaingId) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        String sql = "SELECT COUNT(*) FROM ADS WHERE CAMPAINGS=?";
        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, campaingId);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return resultSet.getInt(1);
    }

    @Override
    public Ad getAdById(Integer id) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        String sql = "SELECT * FROM ADS WHERE ID=?";
        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return new Ad(resultSet.getInt("ID"), resultSet.getString("NAME"),
                resultSet.getInt("STATUS"), getIntArray(resultSet.getArray("PLATFORMS")),
                resultSet.getString("ASSERT_URL"), resultSet.getInt("CAMPAINGS"));
    }

    @Override
    public Campaing getCampaingById(Integer id) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        String sql = "SELECT * FROM CAMPAINGS WHERE ID=?";
        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return new Campaing(resultSet.getInt("ID"), resultSet.getString("NAME"),
                resultSet.getInt("STATUS"), resultSet.getTimestamp("START_DATE"),
                resultSet.getTimestamp("END_DATE"));
    }

    @Override
    public void deleteAdById(Integer id) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        String sql = "DELETE FROM ADS WHERE ID=?";

        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public void deleteCampaignById(Integer id) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        String sql = "DELETE FROM CAMPAINGS WHERE ID=?";

        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<CampaingMiniResponse> getSummaries(String orderBy, Integer page) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        String sql = "SELECT ID, NAME, STATUS FROM CAMPAINGS";

        sql += " WHERE ROWNUM BETWEEN "+ page +" AND " + (page + 5);

        if (!orderBy.isEmpty() && orderBy.trim().length() != 0) {
            sql += " ORDER BY " + orderBy;
        }

        Connection conn = getConnection();

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        List<CampaingMiniResponse> campaingMiniResponseList = new ArrayList<>();

        while (rs.next()) {

            CampaingMiniResponse campaingMiniResponse =
                    new CampaingMiniResponse(rs.getInt("ID"),
                            rs.getString("NAME"), rs.getInt("STATUS"),
                            getCountOfAd(rs.getInt("ID")));

            campaingMiniResponseList.add(campaingMiniResponse);
        }

        return campaingMiniResponseList;
    }

    @Override
    public Campaing createCampaign(CampaingCreate campaingCreate) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        String sql = "INSERT INTO CAMPAINGS(NAME, STATUS, START_DATE, END_DATE) VALUES (?, ?, ?, ?)";
        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, campaingCreate.getName());
        preparedStatement.setInt(2, campaingCreate.getStatus());
        preparedStatement.setTimestamp(3, campaingCreate.getStart_date());
        preparedStatement.setTimestamp(4, campaingCreate.getEnd_date());

        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();

        return getCampaingById(resultSet.getInt("ID"));
    }

    @Override
    public Campaing updateCampaignById(Integer id, CampaingCreate campaingCreate) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        String sql = "UPDATE CAMPAINGS SET NAME=?, STATUS=?, START_DATE=?, END_DATE=? WHERE ID=?";

        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, campaingCreate.getName());
        preparedStatement.setInt(2, campaingCreate.getStatus());
        preparedStatement.setTimestamp(3, campaingCreate.getStart_date());
        preparedStatement.setTimestamp(4, campaingCreate.getEnd_date());

        preparedStatement.setInt(5, id);

        preparedStatement.execute();

        return getCampaingById(id);
    }

    @Override
    public Ad updateAdById(Integer id, AdCreate adCreate) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        String sql = "UPDATE ADS SET NAME=?, STATUS=?, PLATFORMS=?, ASSERT_URL=?, CAMPAINGS=? WHERE ID=?";

        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, adCreate.getName());
        preparedStatement.setInt(2, adCreate.getStatus());
        preparedStatement.setArray(3,
                conn.createArrayOf("INTEGER", Arrays.stream(adCreate.getPlatforms()).boxed().toArray(Integer[]::new)));
        preparedStatement.setString(4, adCreate.getAsset_url());
        preparedStatement.setInt(5, adCreate.getCampaing());
        preparedStatement.setInt(6, id);

        preparedStatement.executeUpdate();

        return getAdById(id);
    }

    @Override
    public Ad createAd(AdCreate adCreate) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        String sql = "INSERT INTO ADS(NAME, STATUS, PLATFORMS, ASSERT_URL, CAMPAINGS) VALUES (?, ?, ?, ?, ?)";

        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, adCreate.getName());
        preparedStatement.setInt(2, adCreate.getStatus());
        preparedStatement.setArray(3,
                conn.createArrayOf("INTEGER", Arrays.stream(adCreate.getPlatforms()).boxed().toArray(Integer[]::new)));
        preparedStatement.setString(4, adCreate.getAsset_url());
        preparedStatement.setInt(5, adCreate.getCampaing());

        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();

        return getAdById(resultSet.getInt("ID"));
    }
}
