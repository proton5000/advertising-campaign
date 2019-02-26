package com.advertising.campaign.dao;

import com.advertising.campaign.models.Ad;
import com.advertising.campaign.models.Campaing;

import java.sql.SQLException;

public interface ApplicationDao {
    Ad getAdById(Integer id) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException;

    Campaing getCampaingById(Integer id) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException;
}
