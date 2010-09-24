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

    @Override
    public void execute(JUGenParam param) {
        if (!param.getFile().getName().endsWith(".java")) {
            return;
        }
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
        PrintWriter pw = null;
        try {
            File junitFile = new File(dir, result.getJavaObjectInfo().getClassName() + ".java");
            System.out.println("Creating " + junitFile);
            pw = new PrintWriter(junitFile);
            pw.print(result.getJUnitContent());
        } catch (FileNotFoundException e) {
            throw new JUGenException(e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
}
