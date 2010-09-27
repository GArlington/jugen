/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

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
