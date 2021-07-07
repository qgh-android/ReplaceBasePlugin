package com.base;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        File file = new File("xxx");
        System.out.println("xxx" + file.isFile());
        User user = new User("xaa", 1);
        user.doHandle();
    }

    String host() {
        return "";
    }

    public static void add1(int age, int age2) {
        Log.i("xxx", "add2,age=" + age);
    }

    public static int add2(int age, int age2) {
        Log.i("xxx", "add2,age=" + age);
        return 20;
    }

    public static int add3(int age) {
        Log.i("xxx", "add2,age=" + age);
        return 20;
    }

    public static void add4() {
        Log.i("xxx", "add2,age=" + 20);
    }

    public static void add5(String[] strings, ArrayList<User> users) {
        Log.i("xxx", "add2,age=" + 20);
    }

    protected void execute() {
        Log.i("xxx", "okhttp AsyncCall url" + host());
    }
}
