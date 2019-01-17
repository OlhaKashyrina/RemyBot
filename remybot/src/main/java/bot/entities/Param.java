package bot.entities;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "params")
@EntityListeners(AuditingEntityListener.class)
public class Param implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long param_id;

    private String text_value;
    private Long number_value;
    private Byte bool_value;

    @NotNull
    private Long attr_id;
    @NotNull
    private Long object_id;

    public Param(String text_value, Long number_value, Byte bool_value, Long attr_id, Long object_id)
    {
        this.text_value = text_value;
        this.number_value = number_value;
        this.bool_value = bool_value;
        this.attr_id = attr_id;
        this.object_id = object_id;
    }

    public  Param(){}

    public Long getParam_id() { return param_id; }

    public void setParam_id(Long param_id) { this.param_id = param_id; }

    public String getText_value() {
        return text_value;
    }

    public void setText_value(String text_value) {
        this.text_value = text_value;
    }

    public Long getNumber_value() { return number_value; }

    public void setNumber_value(Long number_value) { this.number_value = number_value; }

    public Byte getBool_value() { return bool_value;}

    public void setBool_value(Byte bool_value) { this.bool_value = bool_value; }

    public Long getAttr_id() {return attr_id; }

    public void setAttr_id(Long attr_id) { this.attr_id = attr_id; }

    public Long getObject_id() {
        return object_id;
    }

    public void setObject_id(Long object_id) {
        this.object_id = object_id;
    }
}