package org.likui.study;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DoubleAssertTest {
    @Test
    public void testDouble() {
        double d1 = 1.2;
        double d2 = 1.1;

        assertEquals(d1, d2, 0);
    }
}
