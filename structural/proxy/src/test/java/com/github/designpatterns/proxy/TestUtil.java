package com.github.designpatterns.proxy;

import lombok.experimental.UtilityClass;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class TestUtil {
    public static final String CLASS = ".class";
    public static final String DOT = ".";
    public static final String REGEX = "[.]";
    public static final String SLASH = "/";

    public static Set<Class<?>> getClasses(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll(REGEX, SLASH));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(CLASS))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + DOT
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("The class %s was not found".formatted(className));
        }
    }
}
