/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

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
    public void testValidJUnit3Version() throws Exception {
        String[] args = new String[] {"testdata/core", "result/j3b", "templates", "3"};
        try {
            JUGenCLI cli = new JUGenCLI(args);
        } catch (JUGenException e) {
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testValidJUni4tVersion() throws Exception {
        String[] args = new String[] {"testdata/core", "result/j3b", "templates", "4"};
        try {
            JUGenCLI cli = new JUGenCLI(args);
        } catch (JUGenException e) {
            fail(e.getMessage());
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
    public void testValidOverwriteYesKeyword() throws Exception {
        String[] args = new String[] {"testdata/core", "result/j3b", "templates", "10", "--overwrite=yes"};
        try {
            JUGenCLI cli = new JUGenCLI(args);
            fail("JUGenException should be thrown");
        } catch (JUGenException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testValidOverwriteNoKeyword() throws Exception {
        String[] args = new String[] {"testdata/core", "result/j3b", "templates", "10", "--overwrite=no"};
        try {
            JUGenCLI cli = new JUGenCLI(args);
            fail("JUGenException should be thrown");
        } catch (JUGenException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testInvalidExcludeKeyword() throws Exception {
        String[] args = new String[] {"testdata/core", "result/j3b", "templates", "4", 
                "--overwrite=no", "--exclude="};
        try {
            JUGenCLI cli = new JUGenCLI(args);
            fail("JUGenException should be thrown");
        } catch (JUGenException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testValidExcludeKeyword() throws Exception {
        String[] args = new String[] {"testdata/core", "result/j3b", "templates", "4", 
                "--overwrite=no", "--exclude=org.fredy.*   "};
        try {
            JUGenCLI cli = new JUGenCLI(args);
        } catch (JUGenException e) {
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testInvalidOverwriteKeyword() throws Exception {
        String[] args = new String[] {"testdata/core", "result/j3b", "templates", "10", "--overwrite=ssd"};
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
