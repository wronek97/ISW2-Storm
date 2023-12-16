package org.apache.storm.container.cgroup;

import org.apache.storm.container.cgroup.SubSystem;
import org.apache.storm.container.cgroup.SubSystemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

public class TestSubSystem {

    private SubSystemType validType = SubSystemType.cpu;
    private SubSystemType anotherType = SubSystemType.memory;
    private int positive = 1301;
    private int negative = -1997;

    @Test
    public void testConstructor() {
        SubSystem sys;

        sys = new SubSystem(validType, positive, positive, true);
        assertNotNull(sys);
        sys = new SubSystem(validType, positive, positive, false);
        assertNotNull(sys);
        sys = new SubSystem(validType, positive, negative, true);
        assertNotNull(sys);
        sys = new SubSystem(validType, positive, negative, false);
        assertNotNull(sys);
        sys = new SubSystem(validType, negative, positive, true);
        assertNotNull(sys);
        sys = new SubSystem(validType, negative, positive, false);
        assertNotNull(sys);
        sys = new SubSystem(validType, negative, negative, true);
        assertNotNull(sys);
        sys = new SubSystem(validType, negative, negative, false);
        assertNotNull(sys);
    }

    @Test
    public void testSetAndGetType() {
        SubSystem sys = new SubSystem(validType, positive, positive, true);

        assertEquals(validType, sys.getType());
        sys.setType(anotherType);
        assertEquals(anotherType, sys.getType());
    }

    @Test
    public void testSetAndGetHierarchyID() {
        SubSystem sys = new SubSystem(validType, positive, positive, true);

        assertEquals(positive, sys.getHierarchyID());
        sys.setHierarchyID(negative);
        assertEquals(negative, sys.getHierarchyID());
    }

    @Test
    public void testGetCgroupsNum() {
        SubSystem sys = new SubSystem(validType, positive, positive, true);

        assertEquals(positive, sys.getCgroupsNum());
        sys.setCgroupsNum(negative);
        assertEquals(negative, sys.getCgroupsNum());
    }

    @Test
    public void testSetAndIsEnable() {
        SubSystem sys = new SubSystem(validType, positive, positive, true);

        assertEquals(true, sys.isEnable());
        sys.setEnable(false);
        assertEquals(false, sys.isEnable());
    }

    @Test
    public void testHashCode() {
        SubSystem sys = new SubSystem(validType, positive, positive, true);

        assertEquals(31*(31 + validType.hashCode()) + positive, sys.hashCode());
        sys = new SubSystem(null, positive, positive, true);
        assertEquals(31*31 + positive, sys.hashCode());
    }

    @Test
    public void testEquals() {
        SubSystem sys = new SubSystem(validType, positive, positive, true);

        assertFalse(sys.equals(Integer.valueOf(positive)));
        assertFalse(sys.equals(null));
        assertFalse(sys.equals(new SubSystem(anotherType, positive, positive, true)));
        assertFalse(sys.equals(new SubSystem(validType, negative, positive, true)));
        assertTrue(sys.equals(new SubSystem(validType, positive, negative, false)));
    }
}
