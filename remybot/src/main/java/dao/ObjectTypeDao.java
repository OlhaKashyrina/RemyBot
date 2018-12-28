package dao;

import entities.ObjectType;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class ObjectTypeDao extends Dao<ObjectType> {

    @Override
    public ArrayList<ObjectType> getAll(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ArrayList<ObjectType>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<ObjectType>>(){});
        ArrayList<ObjectType> result = response.getBody();
        return  result;
    }
}
