package bot.entities;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(name = "attributes")
@EntityListeners(AuditingEntityListener.class)
public class Attribute implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attr_id;

    @NotBlank
    private String name;

    @NotNull
    private Long object_type_id;

    public Attribute(String name, Long id)
    {
        this.name = name;
        object_type_id = id;
    }

    public  Attribute(){}

    public Long getAttr_id() {
        return attr_id;
    }

    public void setAttr_id(Long attr_id) {
        this.attr_id = attr_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getObject_type_id() {
        return object_type_id;
    }

    public void setObject_type_id(Long object_type_id) {
        this.object_type_id = object_type_id;
    }
}