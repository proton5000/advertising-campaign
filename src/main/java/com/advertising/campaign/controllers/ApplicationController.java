package com.advertising.campaign.controllers;

import com.advertising.campaign.Services.ApplicationServices;
import com.advertising.campaign.models.Ad;
import com.advertising.campaign.models.Campaing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class ApplicationController extends ExceptionHandlerController {

    @Autowired
    private ApplicationServices applicationServices;

    @RequestMapping(value = "/summaries", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSummaries() {
        return "getSummaries";
    }

    @RequestMapping(value = "/campaign/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCampaignById(@PathVariable("id") String id) {
        return "getCampaignById " + id;
    }

    @RequestMapping(value = "/campaign", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createCampaign() {
        return "createCampaign";
    }

    @RequestMapping(value = "/campaign/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateCampaignById(@PathVariable("id") String id) {
        return "updateCampaign " + id;
    }

    @RequestMapping(value = "/campaign/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Campaing> deleteCampaignById(@PathVariable("id") String id) {
        return new ResponseEntity<>(applicationServices.getCampaingById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/ad/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ad> getAdById(@PathVariable("id") Integer id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        return new ResponseEntity<>(applicationServices.getAdById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/ad", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createAd() {
        return "createAd";
    }

    @RequestMapping(value = "/ad/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateAdById(@PathVariable("id") String id) {
        return "updateAdById " + id;
    }

    @RequestMapping(value = "/ad/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteAdById(@PathVariable("id") String id) {
        return "deleteAdById " + id;
    }

}
