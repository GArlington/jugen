package org.fredy.jugen.util;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;

import java.io.File;
import java.io.IOException;

import org.fredy.jugen.JUGenException;
import org.fredy.jugen.bean.JavaObjectInfo;

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
}
