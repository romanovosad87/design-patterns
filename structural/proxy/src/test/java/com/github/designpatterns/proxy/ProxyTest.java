package com.github.designpatterns.proxy;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.designpatterns.proxy.impl.OrderService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProxyTest {
    public static final String PACKAGE_NAME = "com.github.designpatterns.proxy.impl";
    public static final String ORDER_SERVICE = "OrderService";
    public static final String ORDER_SERVICE_PROXY = "OrderServiceProxy";
    public static final String SERVICE_FACTORY = "ServiceFactory";
    public static final String CLIENT = "Client";
    private static Set<Class<?>> classesInPackage;

    @BeforeAll
    static void beforeAll() {
        classesInPackage = TestUtil.getClasses(PACKAGE_NAME);
    }

    @Test
    @Order(1)
    @DisplayName("should be created classes 'OrderService', 'OrderServiceProxy', 'ServiceFactory', 'Client'")
    void getAllClasses() {
        List<String> classNames = classesInPackage.stream()
                .map(Class::getSimpleName)
                .toList();

        assertThat(classesInPackage).hasSizeGreaterThanOrEqualTo(4);
        assertThat(classNames).contains(ORDER_SERVICE, ORDER_SERVICE_PROXY, SERVICE_FACTORY, CLIENT);
    }

    @Test
    @Order(2)
    @DisplayName("proxy class inherits 'OrderServiceClass'")
    void proxyClassShouldExtendOriginalClass() {
        var numberOfClasses = classesInPackage.stream()
                .filter(OrderService.class::isAssignableFrom)
                .count();

        assertThat(numberOfClasses).isGreaterThanOrEqualTo(2);
    }

    @Test
    @Order(3)
    @DisplayName("factory method should return proxy class")
    @SneakyThrows
    void factoryMethodShouldReturnProxyClass() {
        var serviceFactoryClass = classesInPackage.stream()
                .filter(clazz -> clazz.getSimpleName().equals(SERVICE_FACTORY))
                .findFirst()
                .orElseThrow();

        Constructor<?> constructor = serviceFactoryClass.getDeclaredConstructor();
        Object instance = constructor.newInstance();

        Method[] methods = serviceFactoryClass.getDeclaredMethods();
        var methodReturnOrderService = Arrays.stream(methods)
                .filter(method -> method.getReturnType().equals(OrderService.class))
                .findFirst()
                .orElseThrow();

        Object methodResult = methodReturnOrderService.invoke(instance);
        String className = methodResult.getClass().getSimpleName();

        assertThat(methodReturnOrderService).isNotNull();
        assertThat(className).isEqualTo(ORDER_SERVICE_PROXY);
    }
}