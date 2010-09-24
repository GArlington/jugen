package org.fredy.jugen.cli;

import static org.junit.Assert.*;

import java.io.File;

import org.fredy.jugen.core.JUGenException;
import org.fredy.jugen.test.TestUtils;
import org.junit.After;
import org.junit.Test;

public class JUGenCLITest {

    @After
    public void cleanUp() {
        TestUtils.deleteDirectory(new File("result"));
    }
    
    @Test
    public void testInvalidArguments() throws Exception {
        String[] args = new String[] {"abc", "abc", "abc", "5"};
        try {
            JUGenCLI cli = new JUGenCLI(args);
            fail("JUGenException should be thrown");
        } catch (JUGenException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testMissingArguments() throws Exception {
        String[] args = new String[] {"abc", "abc"};
        try {
            JUGenCLI cli = new JUGenCLI(args);
            fail("JUGenException should be thrown");
        } catch (JUGenException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testProjectDirIsNotADirectory() throws Exception {
        String[] args = new String[] {"pom.xml", "result/j3b", "templates", "4"};
        try {
            JUGenCLI cli = new JUGenCLI(args);
            fail("JUGenException should be thrown");
        } catch (JUGenException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testTemplateDirIsNotADirectory() throws Exception {
        String[] args = new String[] {"testdata/core", "result/j3b", "pom.xml", "4"};
        try {
            JUGenCLI cli = new JUGenCLI(args);
            fail("JUGenException should be thrown");
        } catch (JUGenException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testInvalidJUnitVersion() throws Exception {
        String[] args = new String[] {"testdata/core", "result/j3b", "templates", "10"};
        try {
            JUGenCLI cli = new JUGenCLI(args);
            fail("JUGenException should be thrown");
        } catch (JUGenException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testRunWithJUnit3() throws Exception {
        String[] args = new String[] {"testdata/core", "result/j3b", "templates", "4"};
        JUGenCLI cli = new JUGenCLI(args);
        cli.run();
        assertTrue(new File("result/j3b/org/fredy/jugen/core/JavaObjectInfoTest.java").exists());
        assertTrue(new File("result/j3b/org/fredy/jugen/core/JUGenTest.java").exists());
        assertTrue(new File("result/j3b/org/fredy/jugen/core/util/JUGenUtilsTest.java").exists());
        assertTrue(new File("result/j3b/org/fredy/jugen/core/util/JUGenVisitorTest.java").exists());
    }
    
    @Test
    public void testRunWithJUnit4() throws Exception {
        String[] args = new String[] {"testdata/core", "result/j4b", "templates", "4"};
        JUGenCLI cli = new JUGenCLI(args);
        cli.run();
        assertTrue(new File("result/j4b/org/fredy/jugen/core/JavaObjectInfoTest.java").exists());
        assertTrue(new File("result/j4b/org/fredy/jugen/core/JUGenTest.java").exists());
        assertTrue(new File("result/j4b/org/fredy/jugen/core/util/JUGenUtilsTest.java").exists());
        assertTrue(new File("result/j4b/org/fredy/jugen/core/util/JUGenVisitorTest.java").exists());
    }
}
