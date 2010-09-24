package org.fredy.jugen.cli;

import java.io.File;

import org.fredy.jugen.Executable;
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
    private String projectDir;
    private String destinationDir;
    private String templateDir;
    private Template template;
    private JUGen jugen = new JUGen();
    
    public JUGenCLI(String[] args) {
        validateArgs(args);
        projectDir = args[0];
        destinationDir = args[1];
        templateDir = args[2];
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
        jugen.generateJUnit(param);
    }
    
    private void validateArgs(String[] args) {
        if (args.length != 4) {
            throw new JUGenException(getUsage());
        }
        if (!new File(args[0]).isDirectory()) {
            throw new JUGenException(getUsage());
        }
        if (!new File(args[2]).isDirectory()) {
            throw new JUGenException(getUsage());
        } 
        if (!JUNIT3_VERSION.equals(args[3].trim()) && !JUNIT4_VERSION.equals(args[3].trim())) {
            throw new JUGenException(getUsage());
        } 
    }

    private String getUsage() {
        return "Usage: java -jar JUGenCLI <project_dir> <destination_dir> " +
        		"<template_dir> <junit_version>";
    }
}
