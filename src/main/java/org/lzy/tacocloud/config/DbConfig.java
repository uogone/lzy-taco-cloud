package org.lzy.tacocloud.config;

import org.lzy.tacocloud.data.IngredientRepository;
import org.lzy.tacocloud.data.OrderRepository;
import org.lzy.tacocloud.data.UserRepository;
import org.lzy.tacocloud.domain.Ingredient;
import org.lzy.tacocloud.domain.Taco;
import org.lzy.tacocloud.domain.TacoOrder;
import org.lzy.tacocloud.domain.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@Configuration
public class DbConfig {

    @Bean
    public CommandLineRunner ingredientLoader(IngredientRepository repo) {
        return args -> {
            repo.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
            repo.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
            repo.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
            repo.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
            repo.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
            repo.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
            repo.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
            repo.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
            repo.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
            repo.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
        };
    }

    @Bean
    public CommandLineRunner orderLoader(OrderRepository repo) {
        return args -> {
            Taco taco1 = new Taco();
            taco1.setCreatedTime(new Date());
            taco1.setName("taco1");
            taco1.addIngredient(new Ingredient("SRCR"));
            taco1.addIngredient(new Ingredient("FLTO"));

            Taco taco2 = new Taco();
            taco2.setCreatedTime(new Date());
            taco2.setName("taco2");
            taco2.addIngredient(new Ingredient("FLTO"));

            TacoOrder order = new TacoOrder();
            order.setId("order1");
            order.addTaco(taco1);
            order.addTaco(taco2);
            order.setCreatedTime(new Date());
            order.setCvv("cc");
            order.setCcExpiration("ce");
            order.setCcNumber("cn");
            order.setDeliveryCity("city");
            order.setDeliveryName("lzy");
            order.setDeliveryState("st");
            order.setDeliveryStreet("street");
            order.setDeliveryZip("zip");

            repo.save(order);
        };
    }

    @Bean
    public CommandLineRunner userLoader(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            repo.save(new User("lzy", encoder.encode("user")));
        };
    }
}
