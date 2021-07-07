package com.base;

import java.util.ArrayList;

public class User {
    String name;
    int age;
    boolean iscan = false;

    public User(String name, int age) {

    }

    @SuppressWarnings("unchecked")
    public String doHandle() {
        ArrayList<String> list = new ArrayList<>();
        list.add("x");
        java.util.List list1 = new ArrayList();
        list1.add("sss");
        return "";
    }

    public int add(int age, int age2) {

//         Test.add1(age,age2);
        Test.add3(age);
//         Test.add4();
//         "a".equals("q");
        //Test.add5(null,null);
        //Log.i("xxx", "add, age=" + age);
//        Test.add4();
        int re = Test.add2(age, age2);
        return re;
    }

}
