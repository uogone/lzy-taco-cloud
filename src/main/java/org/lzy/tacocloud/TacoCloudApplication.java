package org.lzy.tacocloud;

import org.lzy.tacocloud.data.IngredientRepository;
import org.lzy.tacocloud.data.OrderRepository;
import org.lzy.tacocloud.domain.Ingredient;
import org.lzy.tacocloud.domain.Ingredient.Type;
import org.lzy.tacocloud.domain.IngredientRef;
import org.lzy.tacocloud.domain.Taco;
import org.lzy.tacocloud.domain.TacoOrder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class TacoCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

    @Bean
    public CommandLineRunner ingredientLoader(IngredientRepository repo) {
        return args -> {
            repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
            repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
            repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
            repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
            repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
            repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
            repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
            repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
            repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
            repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
        };
    }

    @Bean
    public CommandLineRunner orderLoader(OrderRepository repo) {
        return args -> {
            Taco taco1 = new Taco();
            taco1.setCreatedTime(new Date());
            taco1.setName("taco1");
            taco1.addIngredient(new IngredientRef("SRCR"));
            taco1.addIngredient(new IngredientRef("FLTO"));

            Taco taco2 = new Taco();
            taco2.setCreatedTime(new Date());
            taco2.setName("taco2");
            taco2.addIngredient(new IngredientRef("FLTO"));

            TacoOrder order = new TacoOrder();
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
}
