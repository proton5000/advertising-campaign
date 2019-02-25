package com.advertising.campaign.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ads")
@Builder
@Setter
@Getter
public class Ad {


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Integer status;

    @Column(name = "platforms")
    private int[] platforms;

    @Column(name = "asset_url")
    private int[] asset_url;

}
