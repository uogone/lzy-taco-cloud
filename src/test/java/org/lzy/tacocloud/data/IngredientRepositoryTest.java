package org.lzy.tacocloud.data;

import org.junit.jupiter.api.Test;
import org.lzy.tacocloud.domain.Ingredient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;

import javax.annotation.Resource;
import java.util.Optional;

@SpringBootTest
public class IngredientRepositoryTest {

    @Resource
    private IngredientRepository ingredientRepository;

    @Resource
    private JdbcAggregateTemplate jdbcAggregateTemplate;

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
        Ingredient ingredient = new Ingredient("TEST", "test", Ingredient.Type.SAUCE);
        Ingredient after = jdbcAggregateTemplate.insert(ingredient);
    }
}
