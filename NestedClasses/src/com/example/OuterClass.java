package com.example;

public class OuterClass { // top level class

    //some types of nested class 
    public int x = 42;

    public void method1() {
        // local classes are inner classes but not members of the outer class
        class LocalClass { // local - локальный = внутри метода

            public void localPrint() {
                System.out.println("In local class");
                System.out.println(x);
            }
        }
        LocalClass lc = new LocalClass();
        lc.localPrint();
    }

    public void method2() {
        Runnable r = new Runnable() {

            @Override
            public void run() { // anonymous local class - внутри метода и без имени
                System.out.println("In an anonymous local class method");
                System.out.println(x);
            }
        };
        r.run();
    }
    public Runnable r = new Runnable() {

        @Override
        public void run() { // anonymous class - без имени
            System.out.println("In an anonymous class method");
            System.out.println(x);
        }
    };
    Object o = new Object() {

        @Override
        public String toString() {
            return "In an anonymous class method";
        }
    };// anonymous class - без имени

    public class InnerClass {//nested class - внутренний нестатический

        // hides OuterClass x
        public int x = 43;
        //static requires final
        public static final int y = 44;

        public void innerPrint() {
            System.out.println("In a inner class method");
            System.out.println(x);
        }
    }

    // not an inner class because it is static
    public static class StaticNestedClass { // //nested static class - внутренний статический

        public void staticNestedPrint() {
            System.out.println("In a static nested class method");
            //compile error
            //System.out.println(x);
        }
    }

    // nesting...
    public class A {//member class - nested нестатический

        public class B {//member class - nested нестатический

            public void method() {
                class C {// local - локальный = внутри метода
                }
            }
        }
    }
}