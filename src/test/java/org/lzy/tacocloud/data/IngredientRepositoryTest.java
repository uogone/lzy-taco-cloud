package org.lzy.tacocloud.data;

import org.junit.jupiter.api.Test;
import org.lzy.tacocloud.domain.Ingredient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class IngredientRepositoryTest {

    @Resource
    private IngredientRepository ingredientRepository;

    @Test
    public void testFindAll() {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        ingredients.forEach(System.out::println);
    }

    @Test
    public void testFindByCorrectId() {
        Optional<Ingredient> optional = ingredientRepository.findById("SRCR");
        System.out.println(optional.orElse(null));
    }

    @Test
    public void testSave() {
        ingredientRepository.save(new Ingredient("TEST", "test", Ingredient.Type.SAUCE));
        Optional<Ingredient> optional = ingredientRepository.findById("TEST");
        System.out.println(optional.orElse(null));
    }
}
