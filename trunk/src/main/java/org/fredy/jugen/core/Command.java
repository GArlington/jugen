package org.fredy.jugen.core;


/**
 * @author fredy
 */
public interface Command {

    /**
     * Creates a JUnit file.
     * @param param the JUGenParam object
     */
    public void execute(JUGenParam param);
}
