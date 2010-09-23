package org.fredy.jugen;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fredy
 */
public class JavaObjectInfo {

    private String packageName;
    private String className;
    private List<String> publicMethods = new ArrayList<String>();

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getPublicMethods() {
        return publicMethods;
    }

    public void addPublicMethod(String publicMethod) {
        publicMethods.add(publicMethod);
    }
}
