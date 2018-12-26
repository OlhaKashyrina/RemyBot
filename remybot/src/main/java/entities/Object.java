package entities;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "objects")
@EntityListeners(AuditingEntityListener.class)
public class Object implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long object_id;

    private String name;

    @NotNull
    private Long object_type_id;

    public Object(String name, Long id)
    {
        this.name = name;
        object_type_id = id;
    }

    public  Object(){}

    public Long getObject_id() {
        return object_id;
    }

    public void setObject_id(Long object_id) {
        this.object_id = object_id;
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

    @Override
    public String toString() {
        return this.name;
    }
}