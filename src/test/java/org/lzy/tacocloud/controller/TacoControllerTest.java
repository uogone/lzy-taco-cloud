package org.lzy.tacocloud.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TacoController.class)
public class TacoControllerTest {

    @Resource
    private MockMvc mockMvc;

    @Test
    public void testDesignValidTaco() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/design")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("ingredients", "SRCR")
                        .param("name", "L's Taco"))
                .andExpect(model().hasNoErrors())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testDesignEmptyNameTaco() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/design")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("ingredients", "SRCR"))
                .andExpect(model().hasErrors())
                .andExpect(status().is5xxServerError());
    }
}
