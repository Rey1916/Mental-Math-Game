package com.example.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CalculatorController.class)
@Import(CalculatorService.class)
class CalculatorControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void add_works() throws Exception {
        mvc.perform(get("/api/v1/calc/add").param("a","10").param("b","5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(15));
    }

    @Test
    void subtract_works() throws Exception {
        mvc.perform(get("/api/v1/calc/subtract").param("a","10").param("b","5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(5));
    }

    @Test
    void multiply_works() throws Exception {
        mvc.perform(get("/api/v1/calc/multiply").param("a","10").param("b","5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(50));
    }

    @Test
    void divide_works() throws Exception {
        mvc.perform(get("/api/v1/calc/divide").param("a","5").param("b","2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(2.5));
    }

    @Test
    void divide_by_zero_returns_400() throws Exception {
        mvc.perform(get("/api/v1/calc/divide").param("a","5").param("b","0"))
                .andExpect(status().isBadRequest());
    }
}
