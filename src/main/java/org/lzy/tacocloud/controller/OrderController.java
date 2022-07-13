package org.lzy.tacocloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.lzy.tacocloud.data.OrderRepository;
import org.lzy.tacocloud.domain.TacoOrder;
import org.lzy.tacocloud.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/order")
@SessionAttributes({"order"})
public class OrderController {

    @Resource
    private OrderRepository orderRepository;

    @GetMapping
    public String showOrderForm(Model model) {
        TacoOrder order = (TacoOrder) model.getAttribute("order");
        if(order == null) {
            return "redirect:/design";
        }
        model.addAttribute("order", order);
        return "orderForm";
    }

    @ResponseBody
    @PostMapping
    public String processOrder(
            TacoOrder order,
            @SessionAttribute("order") Optional<TacoOrder> orderOptional,
            SessionStatus status,
            @AuthenticationPrincipal User user) {

        if(!orderOptional.isPresent()) {
            return "redirect:/design";
        }
        order.setTacos(orderOptional.get().getTacos());
        order.setCreatedTime(new Date());

        orderRepository.save(order);
        status.setComplete();
        log.info("User: " + user);
        return order.toString();
    }
}
