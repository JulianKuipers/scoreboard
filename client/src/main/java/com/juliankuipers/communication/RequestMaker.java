package com.juliankuipers.communication;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestMaker {
    private static final String host = "http://localhost:8080";
    private static final HttpClient client = HttpClient.newBuilder().build();

    /**
     * This method makes a request to the server with or without authentication and expects a JSONObject.
     * @param path              The path to which the request should be made.
     * @param authentication    True if authentication is necessary, false if not.
     * @return                  A JSONObject with the response.
     * @throws IOException      Thrown if there is an input/output exception.
     * @throws InterruptedException Thrown if there is an interruption.
     */
    public static JSONObject requestJSONObject(String path, boolean authentication) throws IOException, InterruptedException {
        String response = request(path, authentication).body();
        return new JSONObject(response);
    }

    /**
     * This method makes a request to the server with or without authentication and expects a JSONArray.
     * @param path              The path to which the request should be made.
     * @param authentication    True if authentication is necessary, false if not.
     * @return                  A JSONArray with the response.
     * @throws IOException      Thrown if there is an input/output exception.
     * @throws InterruptedException Thrown if there is an interruption.
     */
    public static JSONArray requestJSONArray(String path, boolean authentication) throws IOException, InterruptedException {
        String response = request(path, authentication).body();
        return new JSONArray(response);
    }

    /**
     * This method makes a request to the server with or without authentication.
     * @param path              The path to which the request should be made.
     * @param authentication    True if authentication is necessary, false if not.
     * @return                  A String with the HTTP response.
     * @throws IOException      Thrown if there is an input/output exception.
     * @throws InterruptedException Thrown if there is an interruption.
     */
    public static HttpResponse<String> request(String path, boolean authentication) throws IOException, InterruptedException {
        return authentication ? null : request(path);
    }

    /**
     * This method makes a request to the server without authentication.
     * @param path              The path to which the request should be made.
     * @return                  A String with the HTTP response.
     * @throws IOException      Thrown if there is an input/output exception.
     * @throws InterruptedException Thrown if there is an interruption.
     */
    private static HttpResponse<String> request(String path) throws IOException, InterruptedException {
        URI uri = URI.create(host + path);
        System.out.println(uri);
        HttpRequest request = HttpRequest.newBuilder().GET().uri(uri).build();
        System.out.println(request);
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        System.out.println(response);
        return response;
    }
}
