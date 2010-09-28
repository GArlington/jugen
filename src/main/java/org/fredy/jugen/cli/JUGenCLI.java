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

import java.io.File;

import org.fredy.jugen.core.Executable;
import org.fredy.jugen.core.JUGen;
import org.fredy.jugen.core.JUGenException;
import org.fredy.jugen.core.JUGenParam;
import org.fredy.jugen.core.Template;

/**
 * JUGen command line interface.
 * @author fredy
 */
public class JUGenCLI implements Executable {

    private static final String JUNIT4_VERSION = "4";
    private static final String JUNIT3_VERSION = "3";
    private static final String OVERWRITE = "overwrite";
    private String projectDir;
    private String destinationDir;
    private String templateDir;
    private Template template;
    private boolean overwrite;
    private JUGen jugen = new JUGen();
    
    public JUGenCLI(String[] args) {
        validateArgs(args);
        projectDir = args[0];
        destinationDir = args[1];
        templateDir = args[2];
        overwrite = (args.length == 5) ? true : false;
        if (JUNIT3_VERSION.equals(args[3].trim())) {
            template = Template.JUNIT3;
        } else {
            template = Template.JUNIT4;
        }
    }
    
    @Override
    public void run() {
        JUGenParam param = new JUGenParam();
        param.setFile(new File(projectDir));
        param.setDestDir(new File(destinationDir));
        param.setTemplateDir(new File(templateDir));
        param.setTemplate(template);
        param.setOverwrite(overwrite);
        jugen.generateJUnit(param);
    }
    
    private void validateArgs(String[] args) {
        if (args.length < 4) {
            throw new JUGenException(getUsage());
        }
        if (!new File(args[0]).isDirectory()) {
            throw new JUGenException(args[0] + " is not a directory");
        }
        if (!new File(args[2]).isDirectory()) {
            throw new JUGenException(args[2] + " is not a directory");
        } 
        if (!JUNIT3_VERSION.equals(args[3].trim()) && !JUNIT4_VERSION.equals(args[3].trim())) {
            throw new JUGenException(args[3] + " must be either 3 or 4");
        } 
        if (args.length == 5) {
            if (!OVERWRITE.equals(args[4])) {
                throw new JUGenException("Only overwrite keyword is allowed");
            }
        }
    }

    private String getUsage() {
        return "Usage: java -jar jugen.jar <project_dir> <destination_dir> " +
        		"<template_dir> <junit_version> [overwrite]";
    }
}
