package com.example.bmicalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainActivityTest {

    @Test
    public void testCalculateBMI() {

        double weight = 70.0;
        double height = 1.75;

        double expectedBMI = 22.86;
        double resultBMI = calculateBMI(weight, height);


        assertEquals(expectedBMI, resultBMI, 0.01);
    }


    public double calculateBMI(double weight, double height) {
        return weight / (height * height);
    }
}
