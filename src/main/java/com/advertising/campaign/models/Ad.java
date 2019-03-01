package com.advertising.campaign.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Builder
@Setter
@Getter
public class Ad {

    public Ad(int id, String name, int status, int[] platforms, String asset_url, int campaing) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.platforms = platforms;
        this.asset_url = asset_url;
        this.campaing = campaing;
    }

    public Ad() {
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int status;

    private int[] platforms;

    private String asset_url;

    private int campaing;
}
