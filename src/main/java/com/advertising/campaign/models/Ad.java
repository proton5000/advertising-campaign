package com.advertising.campaign.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Array;

@Builder
@Setter
@Getter
public class Ad {

    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private String name;

    private int status;

    private int[] platforms;

    private String asset_url;

    public Ad(int id, String name, int status, Array platforms, String asset_url) {

    }
}
