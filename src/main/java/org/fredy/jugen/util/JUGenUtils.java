package org.fredy.jugen.util;

import groovy.text.SimpleTemplateEngine;
import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.groovy.control.CompilationFailedException;
import org.fredy.jugen.JUGenException;
import org.fredy.jugen.JavaObjectInfo;

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
        
        return joi;
    }
    
    public static String createJUnit(File templateFile, JavaObjectInfo joi) {
        Map<String, JavaObjectInfo> map = new HashMap<String, JavaObjectInfo>();
        map.put("javaObjectInfo", joi);
        SimpleTemplateEngine engine = new SimpleTemplateEngine();
        try {
            return engine.createTemplate(templateFile).make(map).toString();
        } catch (CompilationFailedException e) {
            throw new JUGenException(e);
        } catch (ClassNotFoundException e) {
            throw new JUGenException(e);
        } catch (IOException e) {
            throw new JUGenException(e);
        }
    }
}
