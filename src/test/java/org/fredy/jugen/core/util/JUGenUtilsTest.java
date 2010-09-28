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

package org.fredy.jugen.core.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.fredy.jugen.core.JUGenResult;
import org.fredy.jugen.core.JavaObjectInfo;
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
        assertNull(joi);
    }

    @Test
    public void testGetJavaObjectInfoForEnum() throws Exception {
        JavaObjectInfo joi = JUGenUtils.getJavaObjectInfo(new File("testdata/TestEnum1.java"));
        assertNull(joi);
    }
    
    @Test
    public void testCreateJUnit4() throws Exception {
        JavaObjectInfo joi = new JavaObjectInfo();
        joi.setPackageName("org.fredy.test");
        joi.setClassName("JUnit4");
        joi.addPublicMethod("doSomething1");
        joi.addPublicMethod("doSomething2");
        JUGenResult result = JUGenUtils.createJUnit(new File("templates/junit4.template"), joi);
        assertEquals("JUnit4", result.getJavaObjectInfo().getClassName());
        assertEquals("JUnit4Test", result.getJavaObjectInfo().getTestClassName());
        assertEquals("org.fredy.test", result.getJavaObjectInfo().getPackageName());
        List<String> publicMethods = result.getJavaObjectInfo().getPublicMethods();
        assertEquals("doSomething1", publicMethods.get(0));
        assertEquals("doSomething2", publicMethods.get(1));
        List<String> testPublicMethods = result.getJavaObjectInfo().getPublicTestMethods();
        assertEquals("testDoSomething1", testPublicMethods.get(0));
        assertEquals("testDoSomething2", testPublicMethods.get(1));
        assertEquals(readFile(new File("testdata/JUnit4Test.java")), result.getJUnitContent());
    }

    @Test
    public void testCreateJUnit3() throws Exception {
        JavaObjectInfo joi = new JavaObjectInfo();
        joi.setPackageName("org.fredy.test");
        joi.setClassName("JUnit3");
        joi.addPublicMethod("doSomething1");
        joi.addPublicMethod("doSomething2");
        JUGenResult result = JUGenUtils.createJUnit(new File("templates/junit3.template"), joi);
        assertEquals("JUnit3", result.getJavaObjectInfo().getClassName());
        assertEquals("JUnit3Test", result.getJavaObjectInfo().getTestClassName());
        assertEquals("org.fredy.test", result.getJavaObjectInfo().getPackageName());
        List<String> publicMethods = result.getJavaObjectInfo().getPublicMethods();
        assertEquals("doSomething1", publicMethods.get(0));
        assertEquals("doSomething2", publicMethods.get(1));
        List<String> testPublicMethods = result.getJavaObjectInfo().getPublicTestMethods();
        assertEquals("testDoSomething1", testPublicMethods.get(0));
        assertEquals("testDoSomething2", testPublicMethods.get(1));
        assertEquals(readFile(new File("testdata/JUnit3Test.java")), result.getJUnitContent());
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
