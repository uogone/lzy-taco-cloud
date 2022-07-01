package org.lzy.tacocloud.data;

import org.lzy.tacocloud.domain.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcIngredientRepository implements IngredientRepository{

    @Resource
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Ingredient> findAll() {
        return jdbcTemplate.query(
                "select id, name, type from ingredient",
                this::mapRowToIngredient
        );
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        List<Ingredient> result = jdbcTemplate.query(
                "select id, name, type from ingredient where id = ?",
                this::mapRowToIngredient,
                id
        );

        return result.isEmpty() ?
                Optional.empty() :
                Optional.of(result.get(0));
    }

    @Override
    public void save(Ingredient ingredient) {
        jdbcTemplate.update(
                "insert into ingredient(id, name, type) values(?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString()
        );
    }

    private Ingredient mapRowToIngredient(ResultSet row, int rowNum) throws SQLException {
        return new Ingredient(
                row.getString("id"),
                row.getString("name"),
                Ingredient.Type.valueOf(row.getString("type"))
        );
    }
}
