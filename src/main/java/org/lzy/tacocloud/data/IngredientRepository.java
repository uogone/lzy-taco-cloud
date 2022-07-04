package org.lzy.tacocloud.data;

import org.lzy.tacocloud.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, String> {

}
