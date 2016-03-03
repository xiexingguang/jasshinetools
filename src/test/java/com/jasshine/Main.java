package com.jasshine;

/**
 * Created by ecuser on 2016/2/24.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(Main.class.getClassLoader());
        System.out.println();
        ClassLoader classLoader = Main.class.getClassLoader();
       // classLoader.getResource()
        System.out.println(classLoader.getSystemClassLoader() );

        new Main().get();
    }

    public void get() {
        System.out.println(getClass().getClassLoader());

    }
}
