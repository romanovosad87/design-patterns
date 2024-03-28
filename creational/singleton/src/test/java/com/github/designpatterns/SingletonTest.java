package com.github.designpatterns;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SingletonTest {

    private static Class<?> clazz;

    @BeforeAll
    static void beforeAll() {
        clazz = Singleton.class;
    }

    @Test
    @Order(1)
    @DisplayName("field should have private static access modifier")
    void shouldHavePrivateAccessModifier() {
        Field[] fields = clazz.getDeclaredFields();
        int modifiers = fields[0].getModifiers();
        boolean isPrivate = Modifier.isPrivate(modifiers);
        boolean isStatic = Modifier.isStatic(modifiers);

        assertThat(fields).hasSize(1);
        assertThat(isPrivate).isTrue();
        assertThat(isStatic).isTrue();
    }

    @Test
    @Order(2)
    @DisplayName("constructor should have private access modifier")
    void shouldHavePrivateConstructor() {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        int modifiers = constructors[0].getModifiers();
        boolean isPrivate = Modifier.isPrivate(modifiers);

        assertThat(constructors).hasSize(1);
        assertThat(isPrivate).isTrue();
    }

    @Test
    @Order(3)
    @DisplayName("should be one method with public access modifier")
    void shouldBeOneMethod() {
        Method[] methods = clazz.getDeclaredMethods();
        int modifiers = methods[0].getModifiers();
        boolean isPublic = Modifier.isPublic(modifiers);

        assertThat(methods).hasSize(1);
        assertThat(isPublic).isTrue();
    }

    @Test
    @Order(3)
    @DisplayName("should return the same instance")
    @SneakyThrows
    void shouldReturnTheSameObject() {
        Method[] methods = clazz.getDeclaredMethods();
        Method methodGetInstance = methods[0];
        int modifiers = methodGetInstance.getModifiers();
        boolean isStatic = Modifier.isStatic(modifiers);
        Class<?> returnType = methodGetInstance.getReturnType();

        Object singleton = methodGetInstance.invoke(null);
        Object singleton2 = methodGetInstance.invoke(null);

        assertThat(isStatic).isTrue();
        assertThat(returnType).isEqualTo(Singleton.class);
        assertThat(singleton).isNotNull().isEqualTo(singleton2);
    }
}