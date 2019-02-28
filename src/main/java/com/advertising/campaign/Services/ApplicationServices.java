package com.advertising.campaign.Services;

import com.advertising.campaign.models.Ad;
import com.advertising.campaign.models.Campaing;
import com.advertising.campaign.models.request.AdCreate;
import com.advertising.campaign.models.request.CampaingCreate;
import com.advertising.campaign.models.response.CampaingMiniResponse;

import java.sql.SQLException;
import java.util.List;

public interface ApplicationServices {
    Ad getAdById(Integer id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

    Campaing getCampaingById(Integer id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

    void deleteAdById(Integer id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

    void deleteCampaignById(Integer id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

    List<CampaingMiniResponse> getSummaries(String orderBy, Integer skip) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

    Campaing createCampaign(CampaingCreate campaingCreate) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

    Campaing updateCampaignById(Integer id, CampaingCreate campaingCreate) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

    Ad updateAdById(Integer id, AdCreate adCreate) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

    Ad createAd(AdCreate adCreate) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;
}
