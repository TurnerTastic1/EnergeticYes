package com.example.EnergeticYes;

import com.example.EnergeticYes.trader.Trader;
import com.example.EnergeticYes.trader.TraderController;
import com.example.EnergeticYes.trader.TraderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TraderController.class)
public class TraderControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    TraderRepository traderRepository;

    Trader trader1 = new Trader(1L, "trader1", "Trader1@mail.com", "password1");
    Trader trader2 = new Trader(2L, "trader2", "Trader2@mail.com", "password2");

    @Test
    public void getTraders_success() throws Exception {
        List<Trader> traders = new ArrayList<>(Arrays.asList(trader1, trader2));

        Mockito.when(traderRepository.findAll()).thenReturn(traders);

        mockMvc.perform(MockMvcRequestBuilders
                .get("trader/"))
                .andExpect(status().isOk());
    }
}
