package org.example;

import jep.SharedInterpreter;

public class JepExample {
    public static void main(String[] args) {
        try (SharedInterpreter interp = new SharedInterpreter()) {
            // Define a Python function
            interp.exec("def add(a, b): return a + b");

            // Call the Python function and get result in Java
            int result = interp.getValue("add(5, 7)", Integer.class);

            System.out.println("Result from Python: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
