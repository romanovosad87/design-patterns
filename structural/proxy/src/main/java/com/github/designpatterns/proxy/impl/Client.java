package com.github.designpatterns.proxy.impl;

/**
 * Implement solution according to description in README.md file
 */
public class Client {
    private static ServiceFactory serviceFactory = new ServiceFactory();
    public static void main(String[] args) {
        // refactor the code according to task description in README.md file
        // so that `OrderService` should have additional logic of mock transaction management
        OrderService orderService = serviceFactory.orderService();
        orderService.processOrders();
    }
}
