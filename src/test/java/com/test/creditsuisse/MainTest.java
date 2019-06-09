package com.test.creditsuisse;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void testSalutationMessage() {
        Main hg = new Main();
        assertEquals(3, hg.tescik());
    }

}
