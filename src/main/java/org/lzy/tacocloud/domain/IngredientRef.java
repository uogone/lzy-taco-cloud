package org.lzy.tacocloud.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class IngredientRef implements Serializable {

    private String ingredient;

    private Long taco;

    private Long tacoKey;

    public IngredientRef(String ingredientId) {
        this.ingredient = ingredientId;
    }
}
