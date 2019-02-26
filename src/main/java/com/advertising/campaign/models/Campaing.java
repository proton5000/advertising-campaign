package com.advertising.campaign.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@Setter
@Getter
public class Campaing {

    public Campaing(int id, String name, int status, Timestamp start_date, Timestamp end_date, int[] arrayAds) {

    }

    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private String name;

    private int status;

    private Timestamp start_date;

    private Timestamp end_date;

    private int[] ads;
}
