package org.lzy.tacocloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Taco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    private Date createdTime;

    @NotBlank(message = "name 不能为空格")
    @Size(min = 3, max = 128, message = "name 长度须在3至128之间")
    private String name;

    @NotEmpty(message = "至少选择一个ingredient")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "taco_id")
    @OrderColumn(name = "ordinal")
    private List<IngredientRef> ingredients = new ArrayList<>();

    public void addIngredient(IngredientRef ingredient) {
        ingredients.add(ingredient);
    }
}
