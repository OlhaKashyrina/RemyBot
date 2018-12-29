package bot.dao;

import bot.entities.Object;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class ObjectDao extends  Dao<Object>{

    @Override
    public ArrayList<Object> getAll(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ArrayList<Object>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<Object>>(){});
        ArrayList<Object> result = response.getBody();
        return  result;
    }
}
