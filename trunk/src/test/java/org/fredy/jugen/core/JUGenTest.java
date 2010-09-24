package org.fredy.jugen.core;

import java.io.File;

import org.fredy.jugen.test.TestUtils;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class JUGenTest {

    @After
    public void cleanUp() {
        TestUtils.deleteDirectory(new File("result"));
    }
    
    @Test
    public void testGenerateJUnitWithJUnit4Template() {
        JUGen jugen = new JUGen();
        JUGenParam param = new JUGenParam();
        param.setFile(new File("testdata/core"));
        param.setDestDir(new File("result/j4a"));
        param.setTemplateDir(new File("templates"));
        param.setTemplate(Template.JUNIT4);
        jugen.generateJUnit(param);
        assertTrue(new File("result/j4a/org/fredy/jugen/core/JavaObjectInfoTest.java").exists());
        assertTrue(new File("result/j4a/org/fredy/jugen/core/JUGenTest.java").exists());
        assertTrue(new File("result/j4a/org/fredy/jugen/core/util/JUGenUtilsTest.java").exists());
        assertTrue(new File("result/j4a/org/fredy/jugen/core/util/JUGenVisitorTest.java").exists());
    }
    
    @Test
    public void testGenerateJUnitWithJUnit3Template() {
        JUGen jugen = new JUGen();
        JUGenParam param = new JUGenParam();
        param.setFile(new File("testdata/core"));
        param.setDestDir(new File("result/j3a"));
        param.setTemplateDir(new File("templates"));
        param.setTemplate(Template.JUNIT3);
        jugen.generateJUnit(param);
        assertTrue(new File("result/j3a/org/fredy/jugen/core/JavaObjectInfoTest.java").exists());
        assertTrue(new File("result/j3a/org/fredy/jugen/core/JUGenTest.java").exists());
        assertTrue(new File("result/j3a/org/fredy/jugen/core/util/JUGenUtilsTest.java").exists());
        assertTrue(new File("result/j3a/org/fredy/jugen/core/util/JUGenVisitorTest.java").exists());
    }
}
