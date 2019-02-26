package com.advertising.campaign.dao;

import com.advertising.campaign.models.Ad;

import java.sql.SQLException;

public interface ApplicationDao {
    Ad getCampaingById(Integer id) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException;
}
