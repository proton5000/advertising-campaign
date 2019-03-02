package com.advertising.campaign.models.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class CampaingCreate {

    public CampaingCreate(String name, int status, Timestamp start_date, Timestamp end_date) {
        this.name = name;
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public CampaingCreate() {
    }

    private String name;

    private int status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Timestamp start_date;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Timestamp end_date;
}
