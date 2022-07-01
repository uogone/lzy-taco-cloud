package org.lzy.tacocloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.lzy.tacocloud.data.IngredientRepository;
import org.lzy.tacocloud.domain.Ingredient;
import org.lzy.tacocloud.domain.Taco;
import org.lzy.tacocloud.domain.TacoOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
@RequestMapping
@SessionAttributes({"order"})
public class TacoController {

    @Resource
    private IngredientRepository ingredientRepository;

    @ModelAttribute
    private void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    StreamSupport.stream(ingredients.spliterator(), false)
                            .filter(x -> x.getType().equals(type))
                            .collect(Collectors.toList()));
        }
    }

    @GetMapping("/design")
    public String showDesignForm(Model model) {
        model.addAttribute("taco", new Taco());
        return "design";
    }

    @GetMapping("/taco/list")
    @ResponseBody
    public String listAllTacos(@SessionAttribute Optional<TacoOrder> order) {
        return order.orElse(new TacoOrder()).getTacos().toString();
    }

    @PostMapping("/design")
    public String processTaco(
            @Valid Taco taco,
            BindingResult result,
            @SessionAttribute("order") Optional<TacoOrder> orderOptional,
            Model model) {

        if(result.hasErrors()) {
            log.error(result.getAllErrors().toString());
            return "design";
        }

        TacoOrder order = orderOptional.orElse(new TacoOrder());
        taco.setCreatedTime(new Date());
        order.addTaco(taco);

        //临时保存在session里
        model.addAttribute("order", order);
        log.info("current order: " + order);

        return "redirect:/order";
    }
}
