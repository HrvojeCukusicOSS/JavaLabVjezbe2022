package com.frontend.test2.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontend.test2.entities.Device;
import com.frontend.test2.entities.Measurement;
import com.frontend.test2.service.interfaces.IFrontendDeviceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class FrontendDeviceService implements IFrontendDeviceService {

    ObjectMapper objectMapper;

    @Override
    public List<Device> getDevices(int page, int size, String field, String direction) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/device?page=" + page + "&size=" + size + "&field=" + field + "&direction=" + direction))
                .GET()
                .header("Content-Type", "application/json")
                .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            var json = response.body();
            var jsonNode = objectMapper.readTree(json);
            return objectMapper.readValue(objectMapper.treeAsTokens(jsonNode), new TypeReference<>() {});
    }

    @Override
    public void AddData(String id, String date, String value) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/device/" + id))
                .GET()
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        var json = response.body();
        var jsonNode = objectMapper.readTree(json);
        var device=  objectMapper.readValue(objectMapper.treeAsTokens(jsonNode), new TypeReference<Device>() {});
        var result = new Measurement(null, Integer.parseInt(value), "kWh", LocalDate.parse(date), device );
        String requestbody = objectMapper.writeValueAsString(result);
        HttpClient clientUpload = HttpClient.newHttpClient();
        HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(requestbody))
                .header("Content-type", "application/json")
                .uri(URI.create("http://localhost:8080/measurement"))
                .build();
        clientUpload.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
