package com.github.designpatterns.proxy.impl;

public class OrderServiceProxy extends OrderService {
    @Override
    public void processOrders() {
        System.out.println("[PROXY] make sure the transaction exists");
        super.processOrders();
        System.out.println("[PROXY] Commit the transaction");
    }
}
