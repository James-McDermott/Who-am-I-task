package com.whoami;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HttpRequestTest {

    @Test
    public void contextLoads() {
    }

    @Test
    public void smokeTestShouldReturn200() throws Exception {
        StringEntity entity = new StringEntity("{ \"first_name\":\"fassddaa\", \"country_code\":\"US\" }", ContentType.APPLICATION_JSON);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("http://localhost:8080/whoami");
        request.setEntity(entity);
        HttpResponse response = httpClient.execute(request);
        System.out.println(response.getStatusLine().getStatusCode());
        assert(response.getStatusLine().getStatusCode() == 200);
    }

    @Test
    public void smokeTestShouldReturn400WhenNoBody() throws Exception {
        StringEntity entity = new StringEntity("", ContentType.APPLICATION_JSON);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("http://localhost:8080/whoami");
        request.setEntity(entity);
        HttpResponse response = httpClient.execute(request);
        System.out.println(response.getStatusLine().getStatusCode());
        assert(response.getStatusLine().getStatusCode() == 400);
    }

}
