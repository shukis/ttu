package com.example.pavel.foodel1;

import android.support.annotation.VisibleForTesting;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Pavel on 16.12.2016.
 */

public class OrderConfirmationTest {
    @Test
    public void testTotalSum(){
        OrderConfirmation oc = new OrderConfirmation();
        int result = oc.totalSum("10","2");
        assertEquals(20,result);
    }

    @Test
    public void testSplitText(){
        OrderConfirmation oc = new OrderConfirmation();
        String[] result = oc.splitText("one,two,three");
        String[] splitted = {"one","two","three"};
        assertEquals(splitted,result);

    }
    @Test
    public void testSeparatePrice(){
        OrderConfirmation oc = new OrderConfirmation();
        String result = oc.separatePrice("Long String12o1");
        assertEquals("121",result);
    }
    @Test
    public void testSeparatePriceResturnNull(){
        OrderConfirmation oc = new OrderConfirmation();
        String result = oc.separatePrice("");
        assertNull(result);
    }
    @Test
    public void testSeparateName(){
        OrderConfirmation oc = new OrderConfirmation();
        String result = oc.separateName("Long String 121€");
        assertEquals("Long String",result);
    }
    @Test
    public void testSeparateNameReturnNull(){
        OrderConfirmation oc = new OrderConfirmation();
        String result = oc.separateName("");
        assertNull(result);
    }
}
