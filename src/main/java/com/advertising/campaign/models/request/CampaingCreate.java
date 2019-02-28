package com.advertising.campaign.models.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class CampaingCreate {

    public CampaingCreate(String name, int status, Timestamp start_date, Timestamp end_date, int[] ads) {
        this.name = name;
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;
        this.ads = ads;
    }

    public CampaingCreate() {
    }



    private String name;

    private int status;

    private Timestamp start_date;

    private Timestamp end_date;

    private int[] ads;
}
