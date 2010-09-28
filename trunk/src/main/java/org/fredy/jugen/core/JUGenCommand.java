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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import org.fredy.jugen.core.util.JUGenUtils;

/**
 * @author fredy
 */
public class JUGenCommand implements Command {

    private static final Logger logger = Logger.getLogger(JUGenCommand.class.getName());
    
    @Override
    public void execute(JUGenParam param) {
        if (!param.getFile().getName().endsWith(".java")) {
            return;
        }
        logger.info("Processing " + param.getFile());
        JavaObjectInfo joi = JUGenUtils.getJavaObjectInfo(param.getFile());
        if (joi == null) {
            return;
        }
        JUGenResult result = JUGenUtils.createJUnit(new File(param.getTemplateDir(),
                param.getTemplate().toString()), joi);
        File dir = new File(param.getDestDir(), result.getJavaObjectInfo().getPackageName()
                .replace(".", File.separator));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File junitFile = new File(dir, result.getJavaObjectInfo().getTestClassName() + ".java");
        if (junitFile.exists()) {
            if (param.isOverwrite()) {
                createFile(junitFile, result.getJUnitContent());
            }
        } else {
            createFile(junitFile, result.getJUnitContent());
        }
    }
    
    private void createFile(File file, String content) {
        PrintWriter pw = null;
        try {
            logger.info("Creating " + file);
            pw = new PrintWriter(file);
            pw.print(content);
        } catch (FileNotFoundException e) {
            throw new JUGenException(e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
}
