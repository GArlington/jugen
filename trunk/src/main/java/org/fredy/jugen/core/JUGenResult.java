package org.fredy.jugen.core;

/**
 * @author fredy
 */
public class JUGenResult {

    private JavaObjectInfo javaObjectInfo;
    private String junitContent;

    public JavaObjectInfo getJavaObjectInfo() {
        return javaObjectInfo;
    }

    public void setJavaObjectInfo(JavaObjectInfo javaObjectInfo) {
        this.javaObjectInfo = javaObjectInfo;
    }

    public String getJUnitContent() {
        return junitContent;
    }

    public void setJUnitContent(String junitContent) {
        this.junitContent = junitContent;
    }
}
