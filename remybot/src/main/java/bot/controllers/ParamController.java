package bot.controllers;

import bot.entities.Param;
import bot.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import bot.repositories.ParamRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/botapi")
public class ParamController {

    @Autowired
    ParamRepository paramRepository;

    @GetMapping("/params")
    public List<Param> getAllAttributes() {
        return paramRepository.findAll();
    }

    @PostMapping("/params")
    public Param createAttribute(@Valid @RequestBody Param param) {
        return paramRepository.save(param);
    }

    @GetMapping("/params/{param_id}")
    public Param getAttributeById(@PathVariable(value = "param_id") Long paramId) {
        return paramRepository.findById(paramId)
                .orElseThrow(() -> new ResourceNotFoundException("Param", "param_id", paramId));
    }

    @PutMapping("/params/{param_id}")
    public Param updateAttribute(@PathVariable(value = "param_id") Long paramId,
                                 @Valid @RequestBody Param paramDetails) {

        Param param = paramRepository.findById(paramId)
                .orElseThrow(() -> new ResourceNotFoundException("Param", "param_id", paramId));

        param.setText_value(paramDetails.getText_value());
        param.setNumber_value(paramDetails.getNumber_value());
        param.setBool_value(paramDetails.getBool_value());
        param.setAttr_id(paramDetails.getAttr_id());
        param.setObject_id(paramDetails.getObject_id());

        Param updatedParam = paramRepository.save(param);
        return updatedParam;
    }

    @DeleteMapping("/params/{param_id}")
    public ResponseEntity<?> deleteAttribute(@PathVariable(value = "param_id") Long paramId) {
        Param param = paramRepository.findById(paramId)
                .orElseThrow(() -> new ResourceNotFoundException("Param", "param_id", paramId));

        paramRepository.delete(param);

        return ResponseEntity.ok().build();
    }
}
