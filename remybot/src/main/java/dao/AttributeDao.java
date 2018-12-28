package dao;

import entities.Attribute;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class AttributeDao extends Dao<Attribute> {

    @Override
    public ArrayList<Attribute> getAll(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ArrayList<Attribute>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<Attribute>>(){});
        ArrayList<Attribute> result = response.getBody();
        return  result;
    }
}
