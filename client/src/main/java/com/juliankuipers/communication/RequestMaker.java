package com.juliankuipers.communication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestMaker {
    private static final String host = "https://localhost:8080/";
    private static final HttpClient client = HttpClient.newBuilder().build();

    public static HttpResponse<String> request(String path, boolean authentication) throws IOException, InterruptedException {
        return authentication ? null : request(path);
    }

    public static HttpResponse<String> request(String path) throws IOException, InterruptedException {
        URI uri = URI.create(host + path);
        HttpRequest request = HttpRequest.newBuilder().GET().uri(uri).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return response;
    }
}
