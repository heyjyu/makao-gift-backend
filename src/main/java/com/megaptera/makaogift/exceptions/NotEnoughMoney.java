package com.megaptera.makaogift.exceptions;

public class NotEnoughMoney extends RuntimeException {
    public NotEnoughMoney() {
        super("Not enough money");
    }
}
