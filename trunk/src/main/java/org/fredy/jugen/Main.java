package org.fredy.jugen;

import org.fredy.jugen.cli.JUGenCLI;
import org.fredy.jugen.gui.JUGenGUI;

/**
 * Main class.
 * @author fredy
 */
public class Main {

    public static void main(String[] args) {
        Executable executable = null;
        try {
            if (args.length == 0) {
                executable = new JUGenGUI();
            } else {
                executable = new JUGenCLI(args);
            }
        executable.run();
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
    }
}