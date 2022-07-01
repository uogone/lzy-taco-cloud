package org.lzy.tacocloud.data;

import org.junit.jupiter.api.Test;
import org.lzy.tacocloud.domain.Ingredient;
import org.lzy.tacocloud.domain.Taco;
import org.lzy.tacocloud.domain.TacoOrder;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
public class OrderRepositoryTest {

    @Resource
    private OrderRepository orderRepository;

    private TacoOrder mockOrder() {
        Taco taco1 = new Taco();
        taco1.setCreatedTime(new Date());
        taco1.setName("taco1");
        taco1.addIngredient(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
        taco1.addIngredient(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));

        Taco taco2 = new Taco();
        taco2.setCreatedTime(new Date());
        taco2.setName("taco2");
        taco2.addIngredient(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));

        TacoOrder order = new TacoOrder();
        order.addTaco(taco1);
        order.addTaco(taco2);
        order.setCreatedTime(new Date());
        order.setCcCVV("cc");
        order.setCcExpiration("ce");
        order.setCcNumber("cn");
        order.setDeliveryCity("city");
        order.setDeliveryName("lzy");
        order.setDeliveryState("state");
        order.setDeliveryStreet("street");
        order.setDeliveryZip("zip");

        return order;
    }

    @Test
    public void testSave() {
        TacoOrder order = mockOrder();
        orderRepository.save(order);
        System.out.println(order);
    }
}
