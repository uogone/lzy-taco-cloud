package org.lzy.tacocloud.data;

import org.lzy.tacocloud.domain.Ingredient;
import org.lzy.tacocloud.domain.Taco;
import org.lzy.tacocloud.domain.TacoOrder;
import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public void save(TacoOrder order) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into Taco_Order "
                + "(delivery_name, delivery_street, delivery_city, "
                + "delivery_state, delivery_zip, cc_number, "
                + "cc_expiration, cc_cvv, placed_at) "
                + "values (?,?,?,?,?,?,?,?,?)",
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        );
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc =
                pscf.newPreparedStatementCreator(
                        Arrays.asList(
                                order.getDeliveryName(),
                                order.getDeliveryStreet(),
                                order.getDeliveryCity(),
                                order.getDeliveryState(),
                                order.getDeliveryZip(),
                                order.getCcNumber(),
                                order.getCcExpiration(),
                                order.getCcCVV(),
                                order.getCreatedTime()));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        long orderId = keyHolder.getKey().longValue();
        order.setId(orderId);
        saveTacos(orderId, order.getTacos());
    }

    private void saveTacos(long orderId, List<Taco> tacos) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into Taco "
                        + "(name, created_at, taco_order, taco_order_key) "
                        + "values (?, ?, ?, ?)",
                Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
        );
        pscf.setReturnGeneratedKeys(true);

        for (int i = 0; i < tacos.size(); i++) {
            Taco taco = tacos.get(i);
            PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(
                    taco.getName(),
                    taco.getCreatedTime(),
                    orderId,
                    i
            ));
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(psc, keyHolder);
            long tacoId = keyHolder.getKey().longValue();
            taco.setId(tacoId);
            saveIngredients(tacoId, taco.getIngredients());
        }
    }

    private void saveIngredients(long tacoId, List<Ingredient> ingredients) {
        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient cur = ingredients.get(i);
            jdbcTemplate.update(
                    "insert into Ingredient_Ref (ingredient, taco, taco_key) "
                            + "values (?, ?, ?)",
                    cur.getId(), tacoId, i
            );
        }
    }
}
