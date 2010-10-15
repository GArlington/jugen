package org.fredy.jugen.core.util;

import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.ModifierSet;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import org.fredy.jugen.core.JavaObjectInfo;

/**
 * @author fredy
 */
public class JUGenVisitor extends VoidVisitorAdapter<JavaObjectInfo> {
    
    @Override
    public void visit(ClassOrInterfaceDeclaration coid, JavaObjectInfo joi) {
        super.visit(coid, joi);
        if (ModifierSet.PUBLIC == coid.getModifiers() && !coid.isInterface()) {
            joi.setClassName(coid.getName());
        }
    }
    
    @Override
    public void visit(PackageDeclaration pd, JavaObjectInfo joi) {
        super.visit(pd, joi);
        joi.setPackageName(pd.getName().toString());
    }
    
    @Override
    public void visit(MethodDeclaration md, JavaObjectInfo joi) {
        super.visit(md, joi);
        if (ModifierSet.PUBLIC == md.getModifiers()) {
            joi.addPublicMethod(md.getName());
        }
        if ((ModifierSet.PUBLIC | ModifierSet.STATIC) == md.getModifiers()) {
            joi.addPublicMethod(md.getName());
        }
    }
}
