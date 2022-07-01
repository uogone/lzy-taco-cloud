package org.lzy.tacocloud.data;

import org.lzy.tacocloud.domain.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository {

    List<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    void save(Ingredient ingredient);
}
