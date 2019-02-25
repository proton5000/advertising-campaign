package com.advertising.campaign;

import com.advertising.campaign.Services.ApplicationServices;
import com.advertising.campaign.Services.ApplicationServicesImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CampaignApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampaignApplication.class, args);

		ApplicationServices applicationServices = new ApplicationServicesImpl();
		applicationServices.createCampaingTable();
		applicationServices.createAdsTable();
	}

}
