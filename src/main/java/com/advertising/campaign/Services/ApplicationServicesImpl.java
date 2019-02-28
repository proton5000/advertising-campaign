package com.advertising.campaign.Services;

import com.advertising.campaign.dao.ApplicationDao;
import com.advertising.campaign.models.Ad;
import com.advertising.campaign.models.Campaing;
import com.advertising.campaign.models.request.CampaingCreate;
import com.advertising.campaign.models.response.CampaingMiniResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ApplicationServicesImpl implements ApplicationServices {

    @Autowired
    private ApplicationDao applicationDao;

    @Override
    public Ad getAdById(Integer id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        return applicationDao.getAdById(id);
    }

    @Override
    public Campaing getCampaingById(Integer id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        return applicationDao.getCampaingById(id);
    }

    @Override
    public void deleteAdById(Integer id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        applicationDao.deleteAdById(id);
    }

    @Override
    public void deleteCampaignById(Integer id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        applicationDao.deleteCampaignById(id);
    }

    @Override
    public List<CampaingMiniResponse> getSummaries(String orderBy, Integer skip) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        return applicationDao.getSummaries(orderBy, skip);
    }

    @Override
    public Campaing createCampaign(CampaingCreate campaingCreate) {
        return applicationDao.createCampaign(campaingCreate);
    }
}
