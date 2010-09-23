package org.fredy.jugen.util;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.fredy.jugen.bean.JavaObjectInfo;
import org.junit.Test;

public class JUGenUtilsTest {

    @Test
    public void testGetJavaObjectInfoForClass() throws Exception {
        JavaObjectInfo joi = JUGenUtils.getJavaObjectInfo(new File("testdata/TestClass1.java"));
        assertEquals("org.fredy.test", joi.getPackageName());
        assertEquals("TestClass1", joi.getClassName());
        List<String> publicMethods = joi.getPublicMethods();
        assertEquals("test1", publicMethods.get(0));
        assertEquals("test2", publicMethods.get(1));
        assertEquals("test3", publicMethods.get(2));
        assertEquals("test6", publicMethods.get(3));
    }

    @Test
    public void testGetJavaObjectInfoForInterface() throws Exception {
        JavaObjectInfo joi = JUGenUtils.getJavaObjectInfo(new File("testdata/TestInterface1.java"));
        assertEquals("org.fredy.test", joi.getPackageName());
        assertNull(joi.getClassName());
        List<String> publicMethods = joi.getPublicMethods();
        assertEquals("test1", publicMethods.get(0));
        assertEquals("test2", publicMethods.get(1));
    }
}
