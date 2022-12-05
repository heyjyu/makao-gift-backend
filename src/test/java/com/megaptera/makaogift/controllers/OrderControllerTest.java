package com.megaptera.makaogift.controllers;

import com.megaptera.makaogift.application.GetOrdersService;
import com.megaptera.makaogift.application.OrderService;
import com.megaptera.makaogift.dtos.OrdersDto;
import com.megaptera.makaogift.exceptions.OrderFailed;
import com.megaptera.makaogift.models.Order;
import com.megaptera.makaogift.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@ActiveProfiles("test")
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private GetOrdersService getOrdersService;

    @SpyBean
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    void setup() {
        token = jwtUtil.encode(1L);
    }

    @Test
    void list() throws Exception {
        given(getOrdersService.getOrders(1L))
                .willReturn(new OrdersDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/orders")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"orders\"")
                ));
    }

    @Test
    void orderSuccess() throws Exception {
        given(orderService.order(any(), any(), any(), any(), any(), any()))
                .willReturn(Order.fake());

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"productId\":1," +
                                "\"count\":1," +
                                "\"to\":\"동길홍\"," +
                                "\"address\":\"서울시 행복구 행복동\"," +
                                "\"message\":\"행복하세요~\"" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("\"id\"")
                ));
    }

    @Test
    void orderWithAuthenticationFails() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .header("Authorization", "Bearer xxx")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"productId\":1," +
                                "\"count\":1," +
                                "\"to\":\"동길홍\"," +
                                "\"address\":\"서울시 행복구 행복동\"," +
                                "\"message\":\"행복하세요~\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void orderWithWrongCount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"productId\":1," +
                                "\"count\":-1," +
                                "\"to\":\"동길홍\"," +
                                "\"address\":\"서울시 행복구 행복동\"," +
                                "\"message\":\"행복하세요~\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void orderWithShortName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"productId\":1," +
                                "\"count\":1," +
                                "\"to\":\"길동\"," +
                                "\"address\":\"서울시 행복구 행복동\"," +
                                "\"message\":\"행복하세요~\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void orderWithLongName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"productId\":1," +
                                "\"count\":1," +
                                "\"to\":\"길동길동길동길동\"," +
                                "\"address\":\"서울시 행복구 행복동\"," +
                                "\"message\":\"행복하세요~\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void orderWithEnglishName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"productId\":1," +
                                "\"count\":1," +
                                "\"to\":\"gildong\"," +
                                "\"address\":\"서울시 행복구 행복동\"," +
                                "\"message\":\"행복하세요~\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void orderWithBlankAddress() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"productId\":1," +
                                "\"count\":1," +
                                "\"to\":\"동길홍\"," +
                                "\"address\":\"\"," +
                                "\"message\":\"행복하세요~\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void orderWithTooLongMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"productId\":1," +
                                "\"count\":1," +
                                "\"to\":\"동길홍\"," +
                                "\"address\":\"서울시 행복구 행복동\"," +
                                "\"message\":\"행복하세요행복하세요행복하세요행복하세요행복하세요행복하세요행복하세요행복하세요행복하세요행복하세요" +
                                "행복하세요행복하세요행복하세요행복하세요행복하세요행복하세요행복하세요행복하세요행복하세요행복하세요~\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void orderFail() throws Exception {
        given(orderService.order(any(), any(), any(), any(), any(), any()))
                .willThrow(new OrderFailed());

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"productId\":1," +
                                "\"count\":1," +
                                "\"to\":\"동길홍\"," +
                                "\"address\":\"서울시 행복구 행복동\"," +
                                "\"message\":\"행복하세요~\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }
}
