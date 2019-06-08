package com.test.creditsuisse;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class HelloGradleTest {

    @Test
    public void testSalutationMessage() {
        HelloGradle hg = new HelloGradle();
        assertEquals(3, hg.tescik());
    }

}
