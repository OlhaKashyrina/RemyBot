package bot.controllers;
import bot.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import bot.entities.Object;
import bot.repositories.ObjectRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/botapi")
public class ObjectController {

    @Autowired
    ObjectRepository objectRepository;

    @GetMapping("/objects")
    public List<Object> getAllObjects() {
        return objectRepository.findAll();
    }

    @PostMapping("/objects")
    public Object createObject(@Valid @RequestBody Object obj) {
        return objectRepository.save(obj);
    }

    @GetMapping("/objects/{obj_id}")
    public Object getObjectById(@PathVariable(value = "obj_id") Long objId) {
        return objectRepository.findById(objId)
                .orElseThrow(() -> new ResourceNotFoundException("Object", "obj_id", objId));
    }

    @PutMapping("/objects/{obj_id}")
    public Object updateObject(@PathVariable(value = "obj_id") Long objId,
                               @Valid @RequestBody Object objDetails) {

        Object object = objectRepository.findById(objId)
                .orElseThrow(() -> new ResourceNotFoundException("Object", "obj_id", objId));

        object.setName(objDetails.getName());
        object.setObject_type_id(objDetails.getObject_type_id());

        Object updatedAttr = objectRepository.save(object);
        return updatedAttr;
    }

    @DeleteMapping("/objects/{obj_id}")
    public ResponseEntity<?> deleteObject(@PathVariable(value = "obj_id") Long objId) {
        Object object = objectRepository.findById(objId)
                .orElseThrow(() -> new ResourceNotFoundException("Object", "obj_id", objId));

        objectRepository.delete(object);

        return ResponseEntity.ok().build();
    }
}