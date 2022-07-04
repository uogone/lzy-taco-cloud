package org.lzy.tacocloud.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class IngredientRef implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "taco_id")
    private Long tacoId;

    private String ingredient;

    public IngredientRef(String ingredientId) {
        this.ingredient = ingredientId;
    }
}
