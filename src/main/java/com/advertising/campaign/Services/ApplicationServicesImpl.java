package com.advertising.campaign.Services;

import com.advertising.campaign.dao.ApplicationDao;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationServicesImpl implements ApplicationServices {

    @Autowired
    private ApplicationDao applicationDao;

    @Override
    public void createCampaingTable() {
        applicationDao.createCampaingTable();
    }

    public void createAdsTable() {
        applicationDao.createAdTable();
    }
}
