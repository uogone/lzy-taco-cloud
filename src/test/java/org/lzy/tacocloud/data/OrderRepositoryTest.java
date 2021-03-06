package org.lzy.tacocloud.data;

import org.junit.jupiter.api.Test;
import org.lzy.tacocloud.domain.IngredientRef;
import org.lzy.tacocloud.domain.Taco;
import org.lzy.tacocloud.domain.TacoOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class OrderRepositoryTest {

    @Resource
    private OrderRepository orderRepository;

    private TacoOrder mockOrder() {
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

        return order;
    }

    @Test
    public void testSave() {
        TacoOrder order = mockOrder();
        TacoOrder after = orderRepository.save(order);
        System.out.println(after);
    }

    @Test
    @Transactional
    public void testFindById() {
        Optional<TacoOrder> order = orderRepository.findById(1L);
        System.out.println(order.orElse(null));
    }
}
