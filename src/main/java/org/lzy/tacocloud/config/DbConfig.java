package org.lzy.tacocloud.config;

import org.lzy.tacocloud.data.IngredientRepository;
import org.lzy.tacocloud.data.OrderRepository;
import org.lzy.tacocloud.data.TacoRepository;
import org.lzy.tacocloud.data.UserRepository;
import org.lzy.tacocloud.domain.Ingredient;
import org.lzy.tacocloud.domain.Ingredient.Type;
import org.lzy.tacocloud.domain.Taco;
import org.lzy.tacocloud.domain.TacoOrder;
import org.lzy.tacocloud.domain.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Date;

@Configuration
public class DbConfig {

    @Bean
    public CommandLineRunner tacoLoader(IngredientRepository ingredientRepo, TacoRepository tacoRepo) {
        return args -> {
            Ingredient flourTortilla = new Ingredient(
                    "FLTO", "Flour Tortilla", Type.WRAP);
            Ingredient cornTortilla = new Ingredient(
                    "COTO", "Corn Tortilla", Type.WRAP);
            Ingredient groundBeef = new Ingredient(
                    "GRBF", "Ground Beef", Type.PROTEIN);
            Ingredient carnitas = new Ingredient(
                    "CARN", "Carnitas", Type.PROTEIN);
            Ingredient tomatoes = new Ingredient(
                    "TMTO", "Diced Tomatoes", Type.VEGGIES);
            Ingredient lettuce = new Ingredient(
                    "LETC", "Lettuce", Type.VEGGIES);
            Ingredient cheddar = new Ingredient(
                    "CHED", "Cheddar", Type.CHEESE);
            Ingredient jack = new Ingredient(
                    "JACK", "Monterrey Jack", Type.CHEESE);
            Ingredient salsa = new Ingredient(
                    "SLSA", "Salsa", Type.SAUCE);
            Ingredient sourCream = new Ingredient(
                    "SRCR", "Sour Cream", Type.SAUCE);

            ingredientRepo.save(flourTortilla);
            ingredientRepo.save(cornTortilla);
            ingredientRepo.save(groundBeef);
            ingredientRepo.save(carnitas);
            ingredientRepo.save(tomatoes);
            ingredientRepo.save(lettuce);
            ingredientRepo.save(cheddar);
            ingredientRepo.save(jack);
            ingredientRepo.save(salsa);
            ingredientRepo.save(sourCream);

            Taco taco1 = new Taco();
            taco1.setName("Carnivore");
            taco1.setIngredients(Arrays.asList(
                    flourTortilla, groundBeef, carnitas,
                    sourCream, salsa, cheddar));
            tacoRepo.save(taco1);

            Taco taco2 = new Taco();
            taco2.setName("Bovine Bounty");
            taco2.setIngredients(Arrays.asList(
                    cornTortilla, groundBeef, cheddar,
                    jack, sourCream));
            tacoRepo.save(taco2);

            Taco taco3 = new Taco();
            taco3.setName("Veg-Out");
            taco3.setIngredients(Arrays.asList(
                    flourTortilla, cornTortilla, tomatoes,
                    lettuce, salsa));
            tacoRepo.save(taco3);
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
