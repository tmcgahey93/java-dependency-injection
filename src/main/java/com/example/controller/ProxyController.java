package com.example.config.controller;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class ProxyController {

    private final CloseableHttpClient httpClient;

    public ProxyController(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @GetMapping("/proxy")
    public ResponseEntity<String> proxy(@RequestParam(defaultValue = "https://httpbin.org/get") String url) {
        HttpGet request = new HttpGet(url);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            String ResponseBody = EntityUtils.toString(response.getEntity());
            return ResponseEntity.ok(ResponseBody);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + e.getMessage());
        }
    }
}
