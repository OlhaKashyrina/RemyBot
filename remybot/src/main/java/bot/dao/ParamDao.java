package bot.dao;

import bot.entities.Param;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class ParamDao extends Dao<Param> {

    @Override
    public ArrayList<Param> getAll(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ArrayList<Param>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<Param>>(){});
        ArrayList<Param> result = response.getBody();
        return  result;
    }
}
