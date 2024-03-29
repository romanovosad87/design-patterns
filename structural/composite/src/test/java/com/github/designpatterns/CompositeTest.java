package com.github.designpatterns;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.reflections.Reflections;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CompositeTest {

    private static Reflections reflections;

    @BeforeAll
    static void beforeAll() {
        reflections = new Reflections("com.github.designpatterns");
    }

    @Test
    @Order(1)
    @DisplayName("should create Circle, Square and Drawing class as subclass of Shape interface")
    void shouldCreateCircleAndSquareClass() {
        Set<Class<? extends Shape>> subTypesOfShape = reflections.getSubTypesOf(Shape.class);

        assertThat(subTypesOfShape).hasSizeGreaterThanOrEqualTo(3);

        List<String> className = subTypesOfShape.stream()
                .map(Class::getSimpleName)
                .toList();

        assertThat(className).contains("Circle", "Square", "Drawing");
    }

    @Test
    @Order(2)
    @DisplayName("should have a field of some Collection interface in Drawing class")
    void shouldHaveCollectionFieldInDrawingClass() {
        var drawingClass = getDrawingClass();
        Field[] fields = drawingClass.getDeclaredFields();
        var collectionField = getCollectionField(fields);

        assertThat(collectionField).isNotEmpty();
    }

    @Test
    @Order(3)
    @DisplayName("should contain methods 'addShape' and 'removeShape' in Drawing class")
    void shouldCreateAddRemoveMethodsInDrawingClass() {
        var drawingClass = getDrawingClass();

        Method[] methods = drawingClass.getDeclaredMethods();
        List<String> methodNames = Arrays.stream(methods)
                .map(Method::getName)
                .toList();

        assertThat(methodNames).contains("addShape", "removeShape");
    }

    @Test
    @Order(4)
    @DisplayName("should be override draw() method in Drawing class")
    void shouldDelegateWorkToChildComponent() {
        var drawingClass = getDrawingClass();

        Method[] methods = drawingClass.getDeclaredMethods();
        var drawMethod = Arrays.stream(methods)
                .filter(method -> method.getName().equals("draw"))
                .findFirst();

        assertThat(drawMethod).isNotEmpty();
        assertThat(drawMethod.get().getReturnType()).isEqualTo(void.class);
    }

    private Class<? extends Shape> getDrawingClass() {

        Set<Class<? extends Shape>> subTypesOfShape = reflections.getSubTypesOf(Shape.class);

        return subTypesOfShape.stream()
                .filter(clazz -> clazz.getSimpleName().equals("Drawing"))
                .findFirst()
                .orElseThrow();
    }

    private Optional<Field> getCollectionField(Field[] fields) {
        return Arrays.stream(fields)
                .filter(field -> Collection.class.isAssignableFrom(field.getType()))
                .findFirst();
    }
}
