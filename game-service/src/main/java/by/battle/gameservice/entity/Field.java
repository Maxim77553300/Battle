package by.battle.gameservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Entity
public class Field extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private FieldName fieldName;
}
