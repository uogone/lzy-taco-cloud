package org.lzy.tacocloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
@RestResource(rel = "tacos", path = "tacos")
public class Taco implements Serializable {

    @Id
    private String id;

    @NotBlank(message = "name 不能为空格")
    @Size(min = 3, max = 128, message = "name 长度须在3至128之间")
    private String name;

    private Date createdTime = new Date();

    @NotEmpty(message = "至少选择一个ingredient")
    private List<Ingredient> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }
}
