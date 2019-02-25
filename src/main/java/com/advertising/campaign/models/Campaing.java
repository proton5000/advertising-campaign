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

    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    private String name;

    private Integer status;

    private Timestamp start_date;

    private Timestamp end_date;

    private int[] ads;

}
