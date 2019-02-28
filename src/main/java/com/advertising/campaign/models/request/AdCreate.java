package com.advertising.campaign.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdCreate {

    private String name;

    private int status;

    private int[] platforms;

    private String asset_url;
}
