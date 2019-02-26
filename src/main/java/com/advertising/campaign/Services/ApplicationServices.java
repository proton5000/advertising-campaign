package com.advertising.campaign.Services;

import com.advertising.campaign.models.Ad;
import java.sql.SQLException;

public interface ApplicationServices {
    Ad getCampaingById(Integer id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;
}
