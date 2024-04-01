package com.github.designpatterns.facade;

import static com.github.designpatterns.facade.TestUtil.getClasses;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FacadeTest {
    private static final String PACKAGE_NAME = "com.github.designpatterns.facade.impl";
    public static final String AUDIO_PLAYER = "AudioPlayer";
    public static final String VIDEO_PLAYER = "VideoPlayer";
    public static final String IMAGE_VIEWER = "ImageViewer";
    public static final String MULTIMEDIA = "Multimedia";
    private static Set<Class<?>> classesInPackage;

    @BeforeAll
    static void beforeAll() {
        classesInPackage = getClasses(PACKAGE_NAME);
    }

    @Test
    @Order(1)
    @DisplayName("should be created classes 'AudioPlayer', 'VideoPlayer', 'ImageViewer', 'Multimedia'")
    void getAllClasses() {
        List<String> classNames = classesInPackage.stream()
                .map(Class::getSimpleName)
                .toList();

        assertThat(classesInPackage).hasSizeGreaterThanOrEqualTo(4);
        assertThat(classNames).contains(AUDIO_PLAYER, VIDEO_PLAYER, IMAGE_VIEWER, MULTIMEDIA);
    }

    @Test
    @Order(2)
    @DisplayName("subsystem classes 'VideoPlayer', 'AudioPlayer', 'ImageViewer' should have at least one method")
    void subsystemClassesShouldHaveAtLeastOneMethod() {
        classesInPackage.stream()
                .filter(clazz -> clazz.getSimpleName().equals(VIDEO_PLAYER)
                || clazz.getSimpleName().equals(AUDIO_PLAYER)
                || clazz.getSimpleName().equals(IMAGE_VIEWER))
                .forEach(clazz -> {
                    Method[] methods = clazz.getDeclaredMethods();
                    assertThat(methods).hasSizeGreaterThanOrEqualTo(1);
                });
    }

    @Test
    @Order(3)
    @DisplayName("facade class (Multimedia) should have fields the instance of subsystems")
    void facadeClassShouldHaveFieldsOfSubsystemClasses() {
        Class<?> multimedia = classesInPackage.stream()
                .filter(clazz -> clazz.getSimpleName().equals(MULTIMEDIA))
                .findFirst()
                .orElseThrow();

        Field[] declaredFields = multimedia.getDeclaredFields();
        var classNames = Arrays.stream(declaredFields)
                .map(field -> field.getType().getSimpleName())
                .toList();

        assertThat(classNames).contains(AUDIO_PLAYER, VIDEO_PLAYER, IMAGE_VIEWER);
    }

    @Test
    @Order(4)
    @DisplayName("facade class (Multimedia) should have at least three methods")
    void facadeClassShouldHaveAtLeastThreeMethods() {
        Class<?> multimedia = classesInPackage.stream()
                .filter(clazz -> clazz.getSimpleName().equals(MULTIMEDIA))
                .findFirst()
                .orElseThrow();

        Method[] methods = multimedia.getDeclaredMethods();

        assertThat(methods).hasSizeGreaterThanOrEqualTo(3);
    }
}
