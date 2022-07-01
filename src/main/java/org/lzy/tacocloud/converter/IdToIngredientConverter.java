package org.lzy.tacocloud.converter;

import org.lzy.tacocloud.data.IngredientRepository;
import org.lzy.tacocloud.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class IdToIngredientConverter implements Converter<String, Ingredient> {

    @Resource
    private IngredientRepository ingredientRepository;

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id).orElse(null);
    }

}
