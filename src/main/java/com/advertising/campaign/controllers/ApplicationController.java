package com.advertising.campaign.controllers;

import com.advertising.campaign.Services.ApplicationServices;
import com.advertising.campaign.models.Ad;
import com.advertising.campaign.models.Campaing;
import com.advertising.campaign.models.request.AdCreate;
import com.advertising.campaign.models.request.CampaingCreate;
import com.advertising.campaign.models.response.CampaingMiniResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class ApplicationController extends ExceptionHandlerController {

    @Autowired
    private ApplicationServices applicationServices;

    @Autowired
    private Gson gson;

    @RequestMapping(value = "/summaries", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CampaingMiniResponse>> getSummaries(
            @RequestParam(value = "skip", required = false, defaultValue = "1") Integer skip,
            @RequestParam(value = "orderBy", required = false, defaultValue = "NAME DESC") String orderBy
    ) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        return new ResponseEntity<>(applicationServices.getSummaries(orderBy, skip), HttpStatus.OK);
    }

    @RequestMapping(value = "/campaign/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Campaing> getCampaignById(@PathVariable("id") Integer id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        return new ResponseEntity<>(applicationServices.getCampaingById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/campaign", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Campaing> createCampaign(@RequestBody CampaingCreate campaingCreate) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        return new ResponseEntity<>(applicationServices.createCampaign(campaingCreate), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/campaign/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Campaing> updateCampaignById(@RequestBody CampaingCreate campaingCreate, @PathVariable("id") Integer id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        applicationServices.updateCampaignById(id, campaingCreate);
        return new ResponseEntity<>(applicationServices.updateCampaignById(id, campaingCreate), HttpStatus.OK);
    }

    @RequestMapping(value = "/campaign/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCampaignById(@PathVariable("id") Integer id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        applicationServices.deleteCampaignById(id);
        return ResponseEntity.ok(gson.toJson("The Campaign with id = " + id + " was successfully removed"));
    }

    @RequestMapping(value = "/ad/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ad> getAdById(@PathVariable("id") Integer id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        return new ResponseEntity<>(applicationServices.getAdById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/ad", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ad> createAd(@RequestBody AdCreate adCreate) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        return new ResponseEntity<>(applicationServices.createAd(adCreate), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/ad/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ad> updateAdById(@RequestBody AdCreate adCreate, @PathVariable("id") Integer id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        return new ResponseEntity<>(applicationServices.updateAdById(id, adCreate), HttpStatus.OK);
    }

    @RequestMapping(value = "/ad/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteAdById(@PathVariable("id") Integer id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        applicationServices.deleteAdById(id);
        return ResponseEntity.ok(gson.toJson("The AD with id = " + id + " was successfully removed"));
    }

}
