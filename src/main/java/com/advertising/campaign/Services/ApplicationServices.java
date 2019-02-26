package com.advertising.campaign.Services;

import com.advertising.campaign.models.Ad;
import com.advertising.campaign.models.Campaing;

import java.sql.SQLException;

public interface ApplicationServices {
    Ad getAdById(Integer id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

    Campaing getCampaingById(Integer id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;
}
