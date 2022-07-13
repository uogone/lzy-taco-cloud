package org.lzy.tacocloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.lzy.tacocloud.data.IngredientRepository;
import org.lzy.tacocloud.data.TacoRepository;
import org.lzy.tacocloud.domain.Ingredient;
import org.lzy.tacocloud.domain.Taco;
import org.lzy.tacocloud.domain.TacoOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
@RequestMapping
@SessionAttributes({"order"})
@CrossOrigin(origins = "*")
public class TacoController {

    @Resource
    private IngredientRepository ingredientRepository;

    @Resource
    private TacoRepository tacoRepository;

    @Value("${server.port}")
    private Integer test;

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

    @GetMapping(value = "/taco/recent", produces = "application/json")
    @ResponseBody
    public List<Taco> listAllTacos() {
        Pageable page = PageRequest.of(0, 20, Sort.by("createdTime").descending());
        //return new ObjectMapper().writeValueAsString(tacoRepository.findAll(page));
        //return new ResponseEntity<>(tacoRepository.findAll(page), HttpStatus.OK);
        return tacoRepository.findAll(page);
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

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return test.toString();
    }
}
