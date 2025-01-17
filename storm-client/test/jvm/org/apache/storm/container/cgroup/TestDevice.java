package org.apache.storm.container.cgroup;

import org.apache.storm.container.cgroup.Device;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;
import java.util.Arrays;
import java.util.Collection;

public class TestDevice {

    @Test
    public void testConstructors() {
        Device dev;
        
        // test with positive, zero and negative values of int
        dev = new Device(1, 1);
        assertNotNull(dev);
        dev = new Device(1, 0);
        assertNotNull(dev);
        dev = new Device(1, -1);
        assertNotNull(dev);
        dev = new Device(0, 1);
        assertNotNull(dev);
        dev = new Device(0, 0);
        assertNotNull(dev);
        dev = new Device(0, -1);
        assertNotNull(dev);
        dev = new Device(-1, 1);
        assertNotNull(dev);
        dev = new Device(-1, 0);
        assertNotNull(dev);
        dev = new Device(-1, -1);
        assertNotNull(dev);

        // test constructor by string
        String param;

        param = "1:1";
        dev = new Device(param);
        assertNotNull(dev);
        param = "1:0";
        dev = new Device(param);
        assertNotNull(dev);
        param = "1:-1";
        dev = new Device(param);
        assertNotNull(dev);
        param = "0:1";
        dev = new Device(param);
        assertNotNull(dev);
        param = "0:0";
        dev = new Device(param);
        assertNotNull(dev);
        param = "0:-1";
        dev = new Device(param);
        assertNotNull(dev);
        param = "-1:1";
        dev = new Device(param);
        assertNotNull(dev);
        param = "-1:0";
        dev = new Device(param);
        assertNotNull(dev);
        param = "-1:-1";
        dev = new Device(param);
        assertNotNull(dev);
        
        // test invalid string
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> new Device("1"));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> new Device("0:"));
        assertThrows(NumberFormatException.class, () -> new Device(":1"));
        assertThrows(NumberFormatException.class, () -> new Device("no_number"));
        assertThrows(NumberFormatException.class, () -> new Device("1:char"));

    }

    @Test
    public void testToString() {
        Device dev;
        
        // test with positive, zero and negative values of int
        dev = new Device(1, 1);
        assertEquals("1:1", dev.toString());
        dev = new Device(1, 0);
        assertEquals("1:0", dev.toString());
        dev = new Device(1, -1);
        assertEquals("1:-1", dev.toString());
        dev = new Device(0, 1);
        assertEquals("0:1", dev.toString());
        dev = new Device(0, 0);
        assertEquals("0:0", dev.toString());
        dev = new Device(0, -1);
        assertEquals("0:-1", dev.toString());
        dev = new Device(-1, 1);
        assertEquals("-1:1", dev.toString());
        dev = new Device(-1, 0);
        assertEquals("-1:0", dev.toString());
        dev = new Device(-1, -1);
        assertEquals("-1:-1", dev.toString());
    }

    @Test
    public void testHashCode() {
        int major = 123;
        int minor = -456;
        Device dev = new Device(major, minor);
        assertEquals(31 * (31 + major) + minor, dev.hashCode());
    }

    @Test
    public void testEquals() {
        Device dev = new Device(1, -1);
        assertTrue(dev.equals(dev));
        assertFalse(dev.equals(null));
        assertFalse(dev.equals(Integer.valueOf(123456)));
        assertFalse(dev.equals(new Device(1, 0)));
        assertFalse(dev.equals(new Device(0, -1)));
        assertTrue(dev.equals(new Device(1, -1)));
    }
}
