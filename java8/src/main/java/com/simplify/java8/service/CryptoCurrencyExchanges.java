package com.simplify.java8.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CryptoCurrencyExchanges {

    @Value("${crypto.api.key}")
    private String coinApiKey;

    public JsonNode getAllExchanges() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CoinAPI-Key", coinApiKey);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> response = restTemplate.exchange(
                "https://rest.coinapi.io/v1/exchanges", HttpMethod.GET,
                requestEntity, JsonNode.class);
        return response.getBody();
    }

}
