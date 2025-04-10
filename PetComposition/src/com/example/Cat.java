package com.example;

public class Cat extends Animal implements Pet {

    private Nameable nameable = new NameableImpl();
    private Ambulatory ambulatory;

    public Cat(String name) {
        super(4);
        ambulatory = new AmbulatoryImpl(4);
        setName(name);
    }

    public Cat() {
        this("Fluffy");
    }

    @Override
    public void setName(String name) {
        nameable.setName(name);
    }

    @Override
    public String getName() {
        return nameable.getName();
    }

    public void walk() {
        ambulatory.walk();
    }
    


    @Override
    public void eat() {
        System.out.println("Cats like to eat spiders and fish.");
    }

    @Override
    public void play() {
        System.out.println(getName() + " likes to play with string.");
    }
    
}