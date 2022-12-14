package com.megaptera.makaogift.controllers;

import com.megaptera.makaogift.application.GetOrderService;
import com.megaptera.makaogift.application.GetOrdersService;
import com.megaptera.makaogift.application.OrderService;
import com.megaptera.makaogift.dtos.OrderDto;
import com.megaptera.makaogift.dtos.OrdersDto;
import com.megaptera.makaogift.dtos.PageMetadataDto;
import com.megaptera.makaogift.dtos.ProductDto;
import com.megaptera.makaogift.exceptions.InvalidUser;
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

import java.time.LocalDateTime;
import java.util.List;

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

    @MockBean
    private GetOrderService getOrderService;

    @SpyBean
    private JwtUtil jwtUtil;

    private String token;
    private ProductDto productDto;
    private OrderDto orderDto;

    @BeforeEach
    void setup() {
        token = jwtUtil.encode(1L);
        productDto = new ProductDto(1L, "??????", "???????????? ?????? ????????? ?????????", 10000L, "?????? ????????? ??????", "https://chickenImage.com");
        orderDto = new OrderDto(1L, productDto, 1, 10000L, "?????????", "????????? ????????? ?????????", "???????????????~", LocalDateTime.now());
    }

    @Test
    void list() throws Exception {
        Long userId = 1L;
        Integer page = 1;
        Integer size = 8;

        given(getOrdersService.getOrders(userId, page, size))
                .willReturn(new OrdersDto(List.of(orderDto), new PageMetadataDto(1)));

        mockMvc.perform(MockMvcRequestBuilders.get("/orders?page=1&size=8")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"totalPages\"")
                ))
                .andExpect(content().string(
                        containsString("\"orders\"")
                ));
    }

    @Test
    void detail() throws Exception {
        given(getOrderService.getOrder(any(), any()))
                .willReturn(orderDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/orders/1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"product\"")
                ));
    }

    @Test
    void detailWithTokenOfOtherPerson() throws Exception {
        given(getOrderService.getOrder(any(), any()))
                .willThrow(InvalidUser.class);

        String wrongToken = jwtUtil.encode(2L);

        mockMvc.perform(MockMvcRequestBuilders.get("/orders/1")
                        .header("Authorization", "Bearer " + wrongToken))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void orderSuccess() throws Exception {
        given(orderService.order(any(), any(), any(), any(), any(), any(), any()))
                .willReturn(Order.fake());

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"productId\":1," +
                                "\"count\":1," +
                                "\"unitPrice\":10000," +
                                "\"to\":\"?????????\"," +
                                "\"address\":\"????????? ????????? ?????????\"," +
                                "\"message\":\"???????????????~\"" +
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
                                "\"unitPrice\":10000," +
                                "\"to\":\"?????????\"," +
                                "\"address\":\"????????? ????????? ?????????\"," +
                                "\"message\":\"???????????????~\"" +
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
                                "\"unitPrice\":10000," +
                                "\"to\":\"?????????\"," +
                                "\"address\":\"????????? ????????? ?????????\"," +
                                "\"message\":\"???????????????~\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void orderWithWrongUnitPrice() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"productId\":1," +
                                "\"count\":1," +
                                "\"unitPrice\":-10000," +
                                "\"to\":\"?????????\"," +
                                "\"address\":\"????????? ????????? ?????????\"," +
                                "\"message\":\"???????????????~\"" +
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
                                "\"unitPrice\":10000," +
                                "\"to\":\"??????\"," +
                                "\"address\":\"????????? ????????? ?????????\"," +
                                "\"message\":\"???????????????~\"" +
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
                                "\"unitPrice\":10000," +
                                "\"to\":\"????????????????????????\"," +
                                "\"address\":\"????????? ????????? ?????????\"," +
                                "\"message\":\"???????????????~\"" +
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
                                "\"unitPrice\":10000," +
                                "\"to\":\"gildong\"," +
                                "\"address\":\"????????? ????????? ?????????\"," +
                                "\"message\":\"???????????????~\"" +
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
                                "\"unitPrice\":10000," +
                                "\"to\":\"?????????\"," +
                                "\"address\":\"\"," +
                                "\"message\":\"???????????????~\"" +
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
                                "\"unitPrice\":10000," +
                                "\"to\":\"?????????\"," +
                                "\"address\":\"????????? ????????? ?????????\"," +
                                "\"message\":\"??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????" +
                                "??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????~\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void orderFail() throws Exception {
        given(orderService.order(any(), any(), any(), any(), any(), any(), any()))
                .willThrow(new OrderFailed());

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"productId\":1," +
                                "\"count\":1," +
                                "\"unitPrice\":10000," +
                                "\"to\":\"?????????\"," +
                                "\"address\":\"????????? ????????? ?????????\"," +
                                "\"message\":\"???????????????~\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }
}
