package com.github.designpatterns;

/**
 * Task Details:
 * Objective: The objective of this task is to create a Java class that demonstrates the Singleton
 * design pattern.
 * <p>
 * Implementation:
 * Implement a Singleton class that ensures only one instance of the class is created
 * throughout the application's lifecycle.
 * </p>
 * Requirements:
 * The Singleton class should have a private constructor to prevent instantiation from outside the class.
 * Provide a public static method that returns the instance of the Singleton class.
 */
public class Singleton {
    // Private static instance variable to hold the single instance of the class
    private static Singleton instance;

    // Private constructor to prevent instantiation from outside the class
    private Singleton() {
    }

    // Public static method to return the singleton instance
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;

    }
}
