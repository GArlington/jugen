package org.fredy.jugen.core;

public enum Template {

    JUNIT3("junit3.template"), JUNIT4("junit4.template");
    
    private String templateName;
    
    Template(String templateName) {
        this.templateName = templateName;
    }
    
    @Override
    public String toString() {
        return templateName;
    }
}
