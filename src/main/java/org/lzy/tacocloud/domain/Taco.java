package org.lzy.tacocloud.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Taco {

    private Long id;

    private Date createdTime;

    @NotBlank(message = "name 不能为空格")
    @Size(min = 3, max = 128, message = "name 长度须在3至128之间")
    private String name;

    @NotEmpty(message = "至少选择一个ingredient")
    private List<Ingredient> ingredients;
}
