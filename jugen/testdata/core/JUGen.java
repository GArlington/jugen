package org.fredy.jugen.core;

import java.io.File;

/**
 * @author fredy
 */
public class JUGen {

    public void generateJUnit(JUGenParam param) {
        executeRecurse(param, new JUGenCommand());
    }
    
    private void executeRecurse(JUGenParam param, Command command) {
        if (param.getFile().isFile()) {
            command.execute(param);
            return;
        }
        File[] files = param.getFile().listFiles();
        for (File f : files) {
           param.setFile(f);
           executeRecurse(param, command); 
        }
    }
}
