package com.simplify.java8;

import com.fasterxml.jackson.databind.JsonNode;
import com.simplify.java8.service.CryptoCurrencyExchanges;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CryptoCurrencyController {

    private final CryptoCurrencyExchanges cryptoCurrencyExchanges;

    public CryptoCurrencyController(CryptoCurrencyExchanges cryptoCurrencyExchanges) {
        this.cryptoCurrencyExchanges = cryptoCurrencyExchanges;
    }

    @GetMapping("/exchanges")
    public JsonNode getAllExchanges() {
        return cryptoCurrencyExchanges.getAllExchanges();
    }

}
