package com.test.creditsuisse;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;

public class EventServiceTest {

    @Test
    public void getTimestampDifferenceTest() {
        long first = new Timestamp(2010,1,1,1,1,1,1).getTime();
        long second = new Timestamp(2010,1,1,1,1,3,1).getTime();
        Assert.assertEquals(2000,second - first);
    }

}
