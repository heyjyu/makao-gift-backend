package com.megaptera.makaogift.dtos;

public class UserDto {
    private String name;
    private Long amount;

    public UserDto() {
    }

    public UserDto(String name, Long amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public Long getAmount() {
        return amount;
    }
}
