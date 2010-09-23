package org.fredy.jugen.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.fredy.jugen.JavaObjectInfo;
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

    @Test
    public void testCreateJUnit4() throws Exception {
        JavaObjectInfo joi = new JavaObjectInfo();
        joi.setPackageName("org.fredy.test");
        joi.setClassName("JUnit4Test");
        joi.addPublicMethod("doSomething1");
        joi.addPublicMethod("doSomething2");
        String junit = JUGenUtils.createJUnit(new File(
                "src/main/resources/templates/junit4.template"), joi);
        assertEquals(readFile(new File("testdata/JUnit4Test.java")), junit);
    }

    @Test
    public void testCreateJUnit3() throws Exception {
        JavaObjectInfo joi = new JavaObjectInfo();
        joi.setPackageName("org.fredy.test");
        joi.setClassName("JUnit3Test");
        joi.addPublicMethod("doSomething1");
        joi.addPublicMethod("doSomething2");
        String junit = JUGenUtils.createJUnit(new File(
                "src/main/resources/templates/junit3.template"), joi);
        assertEquals(readFile(new File("testdata/JUnit3Test.java")), junit);
    }
    
    private String readFile(File file) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String text = "";
        while ((text = br.readLine()) != null) {
            sb.append(text).append("\n");
        }
        return sb.toString();
    }
}
