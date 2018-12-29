package bot.entities;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "object_types")
@EntityListeners(AuditingEntityListener.class)
public class ObjectType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long object_type_id;

    @NotBlank
    private String name;

    public ObjectType(String name)
    {
        this.name = name;
    }

    public  ObjectType(){}

    public Long getObject_type_id() {
        return object_type_id;
    }

    public void setObject_type_id(Long object_type_id) {
        this.object_type_id = object_type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}