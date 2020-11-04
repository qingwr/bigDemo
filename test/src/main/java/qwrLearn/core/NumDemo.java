package qwrLearn.core;

public class NumDemo {

    public static void main(String[] args) {
        listEnumFeatures();
    }

    public static void listEnumFeatures() {
        System.out.println("Color.values():");
        for (Color c : Color.values()) {
            System.out.println("enum name = " + c.name() + ", ordinal() = " + c.ordinal() + ", getDeclaringClass() = " + c.getDeclaringClass());
        }
    }

    enum Color {RED, GREEN, BLUE}


}
