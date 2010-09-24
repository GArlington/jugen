package org.fredy.jugen.core;

/**
 * @author fredy
 */
public class JUGenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public JUGenException(String message) {
        super(message);
    }
    
    public JUGenException(Throwable cause) {
        super(cause);
    }
    
    public JUGenException(String message, Throwable cause) {
        super(cause);
    }
}
