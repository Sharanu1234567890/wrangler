package io.cdap.wrangler.api.parser;

import org.junit.Assert;
import org.junit.Test;

public class ByteSizeTimeDurationTest {

    // Test for ByteSize parsing
    @Test
    public void testByteSizeParser() {
        // Testing for different byte size units
        ByteSize b1 = new ByteSize("1KB");
        Assert.assertEquals(1024, b1.getBytes()); // 1KB = 1024 bytes
        
        ByteSize b2 = new ByteSize("1MB");
        Assert.assertEquals(1024 * 1024, b2.getBytes()); // 1MB = 1024*1024 bytes

        ByteSize b3 = new ByteSize("1.5MB");
        Assert.assertEquals(1024 * 1024 * 1.5, b3.getBytes(), 0.001); // 1.5MB = 1.5 * 1024*1024 bytes

        // Invalid byte size
        try {
            new ByteSize("1GBX");
            Assert.fail("Exception should have been thrown");
        } catch (IllegalArgumentException e) {
            // expected exception
        }
    }

    // Test for TimeDuration parsing
    @Test
    public void testTimeDurationParser() {
        // Testing for different time duration units
        TimeDuration t1 = new TimeDuration("1s");
        Assert.assertEquals(1000, t1.getMilliseconds()); // 1 second = 1000 milliseconds

        TimeDuration t2 = new TimeDuration("1.5s");
        Assert.assertEquals(1500, t2.getMilliseconds(), 0.001); // 1.5 seconds = 1500 milliseconds

        TimeDuration t3 = new TimeDuration("2m");
        Assert.assertEquals(120000, t3.getMilliseconds()); // 2 minutes = 120000 milliseconds

        TimeDuration t4 = new TimeDuration("1h");
        Assert.assertEquals(3600000, t4.getMilliseconds()); // 1 hour = 3600000 milliseconds

        // Invalid time duration
        try {
            new TimeDuration("2xx");
            Assert.fail("Exception should have been thrown");
        } catch (IllegalArgumentException e) {
            // expected exception
        }
    }
}
