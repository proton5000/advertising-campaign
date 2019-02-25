package com.advertising.campaign.Services;

import com.advertising.campaign.dao.ApplicationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ApplicationServicesImpl implements ApplicationServices {

    @Autowired
    private ApplicationDao applicationDao;

    @Override
    public void createCampaingsTable() {
        applicationDao.createCampaingsTable();
    }

    public void createAdsTable() {
        applicationDao.createAdTable();
    }
}
