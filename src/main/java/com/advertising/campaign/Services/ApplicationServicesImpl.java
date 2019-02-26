package com.advertising.campaign.Services;

import com.advertising.campaign.dao.ApplicationDao;
import com.advertising.campaign.models.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class ApplicationServicesImpl implements ApplicationServices {

    @Autowired
    private ApplicationDao applicationDao;

    @Override
    public Ad getCampaingById(Integer id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        return applicationDao.getCampaingById(id);
    }
}
