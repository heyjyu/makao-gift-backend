package com.megaptera.makaogift.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class OrderRequestDto {
    private Long productId;

    @Min(value = 1)
    private Integer count;

    @Min(value = 0)
    private Long unitPrice;

    @Pattern(regexp = "^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{3,7}$")
    private String to;

    @NotBlank
    private String address;

    @Length(max = 100)
    private String message;

    public Long getProductId() {
        return productId;
    }

    public Integer getCount() {
        return count;
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public String getTo() {
        return to;
    }

    public String getAddress() {
        return address;
    }

    public String getMessage() {
        return message;
    }
}
