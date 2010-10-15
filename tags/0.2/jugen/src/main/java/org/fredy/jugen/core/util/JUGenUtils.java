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

import groovy.text.SimpleTemplateEngine;
import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.groovy.control.CompilationFailedException;
import org.fredy.jugen.core.JUGenException;
import org.fredy.jugen.core.JUGenResult;
import org.fredy.jugen.core.JavaObjectInfo;

/**
 * @author fredy
 */
public class JUGenUtils {

    private JUGenUtils() {
    }
    
    public static JavaObjectInfo getJavaObjectInfo(File javaFile) {
        if (!javaFile.getName().endsWith(".java")) {
            throw new JUGenException("The file is not a Java file");
        }
        CompilationUnit cu = null;
        try {
            cu = JavaParser.parse(javaFile);
        } catch (ParseException e) {
            throw new JUGenException(e);
        } catch (IOException e) {
            throw new JUGenException(e);
        }
        JUGenVisitor jv = new JUGenVisitor();
        JavaObjectInfo joi = new JavaObjectInfo();
        jv.visit(cu, joi);
        
        if (joi.getClassName() == null) {
            return null;
        } else {
            return joi;
        }
    }
    
    public static JUGenResult createJUnit(File templateFile, JavaObjectInfo joi) {
        JUGenResult result = new JUGenResult();
        Map<String, JavaObjectInfo> map = new HashMap<String, JavaObjectInfo>();
        JavaObjectInfo tjoi = createTestJavaObjectInfo(joi);
        map.put("javaObjectInfo", tjoi);
        result.setJavaObjectInfo(tjoi);
        SimpleTemplateEngine engine = new SimpleTemplateEngine();
        try {
            result.setJUnitContent(engine.createTemplate(templateFile).make(map).toString());
            return result;
        } catch (CompilationFailedException e) {
            throw new JUGenException(e);
        } catch (ClassNotFoundException e) {
            throw new JUGenException(e);
        } catch (IOException e) {
            throw new JUGenException(e);
        }
    }
    
    private static JavaObjectInfo createTestJavaObjectInfo(JavaObjectInfo joi) {
        JavaObjectInfo tjoi = new JavaObjectInfo();
        tjoi.setClassName(joi.getClassName());
        tjoi.setTestClassName(joi.getClassName() + "Test");
        tjoi.setPackageName(joi.getPackageName());
        Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
        for (String m : joi.getPublicMethods()) {
            if (map.containsKey(m)) {
                map.get(m).set(0, (map.get(m).get(0)+1));
            } else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(1); // the number of methods that has the same name
                list.add(0); // the current counter
                map.put(m, list);
            }
        }

        for (String m : joi.getPublicMethods()) {
            String method = m;
            if (map.get(m).get(0) > 1) {
                int counter = map.get(m).get(1) + Integer.valueOf(1);
                method += counter;
                map.get(m).set(1, counter);
            } 
            tjoi.addPublicMethod(method);
            tjoi.addPublicTestMethod("test" + String.valueOf(method.charAt(0)).toUpperCase() +
                    method.substring(1));
        }
        
        return tjoi;
    }
}
