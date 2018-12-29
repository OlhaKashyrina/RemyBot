package bot.dao;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class Dao<T> {

    public T getById(String url, Class<T> type) {
        RestTemplate restTemplate = new RestTemplate();
        T result = restTemplate.getForObject(url, type);
        return result;
    }

    public ArrayList<T> getAll(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ArrayList<T>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<T>>(){});
        ArrayList<T> result = response.getBody();
        return  result;
    }

    public void insert(String url, T object, Class<T> type)
    {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<T> request = new HttpEntity<T>(object);
        ResponseEntity<T> response = restTemplate
                .exchange(url, HttpMethod.POST, request, type);

    }

    public void update(String url, T updatedInstance)
    {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<T> requestUpdate = new HttpEntity<T>(updatedInstance);
        restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, Void.class);
    }

    public void delete(String url)
    {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url);
    }
}