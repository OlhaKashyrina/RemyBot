package bot.controllers;

import bot.entities.ObjectType;
import bot.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import bot.repositories.ObjectTypeRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/botapi")
public class ObjectTypeController {

    @Autowired
    ObjectTypeRepository typeRepository;

    @GetMapping("/types")
    public List<ObjectType> getAllTypes() {
        return typeRepository.findAll();
    }

    @PostMapping("/types")
    public ObjectType createType(@Valid @RequestBody ObjectType attr) {
        return typeRepository.save(attr);
    }

    @GetMapping("/types/{type_id}")
    public ObjectType getTypeById(@PathVariable(value = "type_id") Long typeId) {
        return typeRepository.findById(typeId)
                .orElseThrow(() -> new ResourceNotFoundException("ObjectType", "type_id", typeId));
    }

    @PutMapping("/types/{type_id}")
    public ObjectType updateType(@PathVariable(value = "type_id") Long typeId,
                                 @Valid @RequestBody ObjectType typeDetails) {

        ObjectType type = typeRepository.findById(typeId)
                .orElseThrow(() -> new ResourceNotFoundException("ObjectType", "type_id", typeId));

        type.setName(typeDetails.getName());

        ObjectType updatedType = typeRepository.save(type);
        return updatedType;
    }

    @DeleteMapping("/types/{type_id}")
    public ResponseEntity<?> deleteType(@PathVariable(value = "type_id") Long typeId) {
        ObjectType type = typeRepository.findById(typeId)
                .orElseThrow(() -> new ResourceNotFoundException("ObjectType", "type_id", typeId));

        typeRepository.delete(type);

        return ResponseEntity.ok().build();
    }
}
