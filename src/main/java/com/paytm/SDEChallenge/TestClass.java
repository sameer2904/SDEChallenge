package com.paytm.SDEChallenge;

public class TestClass {

    public static void main(String[] args) {
        MovingAverage movingAverage = new MovingAverageImpl(4);
        movingAverage.addElement(3);
        System.out.println(movingAverage.getAverage());
        movingAverage.addElement(5);
        System.out.println(movingAverage.getAverage());
        movingAverage.addElement(2);
        System.out.println(movingAverage.getAverage());
        movingAverage.addElement(8);
        System.out.println(movingAverage.getAverage());
        movingAverage.addElement(1);
        System.out.println(movingAverage.getAverage());
        movingAverage.addElement(2);
        System.out.println(movingAverage.getAverage());
        movingAverage.addElement(9);
        System.out.println(movingAverage.getAverage());
        movingAverage.addElement(1.5f);
        System.out.println(movingAverage.getAverage());
        movingAverage.addElement(7.5f);
        System.out.println(movingAverage.getAverage());
        movingAverage.addElement(5.5f);
        System.out.println(movingAverage.getAverage());
    }
}
