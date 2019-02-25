package com.advertising.campaign.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Builder
@Setter
@Getter
public class Ad {



    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    private String name;

    private Integer status;

    private int[] platforms;

    private int[] asset_url;

}
