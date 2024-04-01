package com.github.designpatterns.proxy.impl;

/**
 * Implement solution according to description in README.md file
 */
public class Client {
    public static void main(String[] args) {
        // refactor the code according to task description in README.md file
        // so that `OrderService` should have additional logic of mock transaction management
        OrderService orderService = new OrderService();
        orderService.processOrders();
    }
}
