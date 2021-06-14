package com.misa.store.db;

import java.io.IOException;
import java.net.*;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Connect {

    private final String USERNAME = "misa";
    private final String PASSWORD = "123";

    private String getAuthorizationHeader() {
        String auth = USERNAME + ":" + PASSWORD;
        byte[] encodedAuth = Base64.getEncoder().encode(
                auth.getBytes(StandardCharsets.ISO_8859_1));
        String authHeader = "Basic " + new String(encodedAuth);

        return authHeader;
    }

    public String get(String urlToRead) throws IOException{
        HttpGet request = new HttpGet(urlToRead);
        request.setHeader(HttpHeaders.AUTHORIZATION, getAuthorizationHeader());

        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(request);

        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);

        return readResponse(response);
    }

    private String readResponse(HttpResponse response) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));
        String line;
        while((line = br.readLine()) != null)
            result.append(line);

        br.close();

        return result.toString();
    }

    public String post(String url, String payload) throws Exception{
        try(CloseableHttpClient client = HttpClientBuilder.create().build()){
            HttpPost request = new HttpPost(url);
            request.setHeader("User-Agent", "Java client");
            request.setHeader("Content-type", "application/json");
            request.setHeader(HttpHeaders.AUTHORIZATION, getAuthorizationHeader());
            request.setEntity(new StringEntity(payload));
            HttpResponse response = client.execute(request);

            System.out.println(response.getStatusLine().getStatusCode());

            return readResponse(response);
        }
    }

    public String delete(String urlToRead) throws IOException {
        HttpDelete request = new HttpDelete(urlToRead);
        request.setHeader(HttpHeaders.AUTHORIZATION, getAuthorizationHeader());

        HttpClient client = HttpClientBuilder.create().build();

        HttpResponse response = client.execute(request);

        return readResponse(response);
    }
}
