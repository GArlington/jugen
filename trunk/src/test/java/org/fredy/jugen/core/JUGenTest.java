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

package org.fredy.jugen.core;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;

import org.fredy.jugen.test.TestUtils;
import org.junit.After;
import org.junit.Test;

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
        param.setOverwrite(false);
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
        param.setOverwrite(false);
        jugen.generateJUnit(param);
        assertTrue(new File("result/j3a/org/fredy/jugen/core/JavaObjectInfoTest.java").exists());
        assertTrue(new File("result/j3a/org/fredy/jugen/core/JUGenTest.java").exists());
        assertTrue(new File("result/j3a/org/fredy/jugen/core/util/JUGenUtilsTest.java").exists());
        assertTrue(new File("result/j3a/org/fredy/jugen/core/util/JUGenVisitorTest.java").exists());
    }
    
    @Test
    public void testGenerateJUnitWithExcludeList() {
        JUGen jugen = new JUGen();
        JUGenParam param = new JUGenParam();
        param.setFile(new File("testdata/core"));
        param.setDestDir(new File("result/j4b"));
        param.setTemplateDir(new File("templates"));
        param.setTemplate(Template.JUNIT4);
        param.setOverwrite(false);
        param.setExcludeList(Arrays.asList(new String[] {"org.fredy.jugen.*.*JUGen*"}));
        jugen.generateJUnit(param);
        assertTrue(new File("result/j4b/org/fredy/jugen/core/JavaObjectInfoTest.java").exists());
    }
}
