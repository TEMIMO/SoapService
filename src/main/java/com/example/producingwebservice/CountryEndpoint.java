package com.example.producingwebservice;

import myxmlnamespace.Country;
import myxmlnamespace.GetCountryRequest;
import myxmlnamespace.GetCountryResponse;
import myxmlnamespace.SetCountryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {
    private static final String NAMESPACE_URI = "myXmlNamespace";

    @Autowired
    public CountryRepo countryRepo;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();

        Country country = new Country();
        CountryDB countryDB = countryRepo.findByName(request.getName());

        country.setName(countryDB.getName());
        country.setPopulation(countryDB.getPopulation());
        country.setCapital(countryDB.getCapital());
        country.setCurrency(countryDB.getCurrency());

        response.setCountry(country);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "setCountryRequest")
    @ResponsePayload
    public GetCountryResponse setCountry(@RequestPayload SetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();

        Country country = new Country();

        country.setName(request.getName());
        country.setCapital(request.getCapital());
        country.setCurrency(request.getCurrency());
        country.setPopulation(request.getPopulation());

        CountryDB countryDB = new CountryDB();
        countryDB.setName(request.getName());
        countryDB.setCapital(request.getCapital());
        countryDB.setCurrency(request.getCurrency().toString());
        countryDB.setPopulation(request.getPopulation());

        countryRepo.save(countryDB);

        response.setCountry(country);

        return response;
    }
}