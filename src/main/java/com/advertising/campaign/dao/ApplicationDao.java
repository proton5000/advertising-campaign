package com.advertising.campaign.dao;

import com.advertising.campaign.models.Ad;
import com.advertising.campaign.models.Campaing;
import com.advertising.campaign.models.request.AdCreate;
import com.advertising.campaign.models.request.CampaingCreate;
import com.advertising.campaign.models.response.CampaingMiniResponse;

import java.sql.SQLException;
import java.util.List;

public interface ApplicationDao {
    Ad getAdById(Integer id) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException;

    Campaing getCampaingById(Integer id) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException;

    void deleteAdById(Integer id) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException;

    void deleteCampaignById(Integer id) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException;

    List<CampaingMiniResponse> getSummaries(String orderBy, Integer skip) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException;

    Campaing createCampaign(CampaingCreate campaingCreate) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

    Campaing updateCampaignById(Integer id, CampaingCreate campaingCreate) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

    Ad updateAdById(Integer id, AdCreate adCreate) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;
}
