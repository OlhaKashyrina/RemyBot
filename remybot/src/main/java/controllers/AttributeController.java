package controllers;

import entities.Attribute;
import exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.AttributeRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/botapi")
public class AttributeController {

    @Autowired
    AttributeRepository attributeRepository;

    @GetMapping("/attributes")
    public List<Attribute> getAllAttributes() {
        return attributeRepository.findAll();
    }

    @PostMapping("/attributes")
    public Attribute createAttribute(@Valid @RequestBody Attribute attr) {
        return attributeRepository.save(attr);
    }

    @GetMapping("/attributes/{attr_id}")
    public Attribute getAttributeById(@PathVariable(value = "attr_id") Long attrId) {
        return attributeRepository.findById(attrId)
                .orElseThrow(() -> new ResourceNotFoundException("Attribute", "attr_id", attrId));
    }

    @PutMapping("/attributes/{attr_id}")
    public Attribute updateAttribute(@PathVariable(value = "attr_id") Long attrId,
                                     @Valid @RequestBody Attribute custDetails) {

        Attribute attr = attributeRepository.findById(attrId)
                .orElseThrow(() -> new ResourceNotFoundException("Attribute", "attr_id", attrId));

        attr.setName(custDetails.getName());
        attr.setObject_type_id(custDetails.getObject_type_id());

        Attribute updatedAttr = attributeRepository.save(attr);
        return updatedAttr;
    }

    @DeleteMapping("/attributes/{attr_id}")
    public ResponseEntity<?> deleteAttribute(@PathVariable(value = "attr_id") Long attrId) {
        Attribute attr = attributeRepository.findById(attrId)
                .orElseThrow(() -> new ResourceNotFoundException("Attribute", "attr_id", attrId));

        attributeRepository.delete(attr);

        return ResponseEntity.ok().build();
    }
}