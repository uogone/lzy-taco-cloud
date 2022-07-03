package org.lzy.tacocloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

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
public class Taco implements Serializable {

    @Id
    private Long id;

    //todo
    private Long orderId;

    /**
     * 该值决定Taco在订单中的次序，越小越先被保存在订单中。
     */
    //todo
    private Long key;

    private Date createdTime;

    @NotBlank(message = "name 不能为空格")
    @Size(min = 3, max = 128, message = "name 长度须在3至128之间")
    private String name;

    @NotEmpty(message = "至少选择一个ingredient")
    private List<IngredientRef> ingredients = new ArrayList<>();

    public void addIngredient(IngredientRef ingredient) {
        ingredients.add(ingredient);
    }
}
