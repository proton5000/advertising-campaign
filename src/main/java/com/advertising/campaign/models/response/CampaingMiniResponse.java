package com.advertising.campaign.models.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampaingMiniResponse {

    public CampaingMiniResponse(int id, String name, int status, int countAds) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.countAds = countAds;
    }

    public CampaingMiniResponse() {
    }

    private int id;

    private String name;

    private int status;

    private int countAds;
}
