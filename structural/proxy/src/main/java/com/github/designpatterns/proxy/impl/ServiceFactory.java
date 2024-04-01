package com.github.designpatterns.proxy.impl;

public class ServiceFactory {
    public OrderService orderService() {
        return new OrderServiceProxy();
    }
}
