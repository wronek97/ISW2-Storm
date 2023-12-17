package org.apache.storm.container.cgroup;

import org.apache.storm.container.cgroup.SubSystemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;
import java.util.Arrays;
import java.util.Collection;

public class TestSubSystemType {

    @Test
    public void testGetSubSystem() {
        // test valid types
        assertEquals(SubSystemType.blkio, SubSystemType.getSubSystem("blkio"));
        assertEquals(SubSystemType.cpu, SubSystemType.getSubSystem("cpu"));
        assertEquals(SubSystemType.cpuacct, SubSystemType.getSubSystem("cpuacct"));
        assertEquals(SubSystemType.cpuset, SubSystemType.getSubSystem("cpuset"));
        assertEquals(SubSystemType.devices, SubSystemType.getSubSystem("devices"));
        assertEquals(SubSystemType.freezer, SubSystemType.getSubSystem("freezer"));
        assertEquals(SubSystemType.memory, SubSystemType.getSubSystem("memory"));
        assertEquals(SubSystemType.perf_event, SubSystemType.getSubSystem("perf_event"));
        assertEquals(SubSystemType.net_cls, SubSystemType.getSubSystem("net_cls"));
        assertEquals(SubSystemType.net_prio, SubSystemType.getSubSystem("net_prio"));

        // test invalid type
        assertThrows(IllegalArgumentException.class, () -> {SubSystemType.getSubSystem("INVALID_TYPE");});
    }
    
}
