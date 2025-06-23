package com.example.config.controller;


@RestController
public class ProxyController {

    private final CloseableHttpClient httpClient;

    public ProxyController(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @GetMapping("/proxy")
    public ResponseEntity<String> proxy(@ResponseParam(defaultValue = "https://httpbin.org/get") string url) {
        HttpGet request = new HttpGet(url);

        try (CloseableHttpResponse response = httpClient.execute(reqeust)) {
            string ResponseBody = EntityUtils.toString(response.getEntity());
            return ResponseEntity.ok(responseBody);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + e.getMessage());
        }
    }
}
