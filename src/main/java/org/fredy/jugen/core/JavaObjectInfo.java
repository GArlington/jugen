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

package org.fredy.jugen.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fredy
 */
public class JavaObjectInfo {

    private String packageName;
    private String className;
    private String testClassName;
    private List<String> publicMethods = new ArrayList<String>();
    private List<String> publicTestMethods = new ArrayList<String>();

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

    public void setTestClassName(String testClassName) {
        this.testClassName = testClassName;
    }

    public String getTestClassName() {
        return testClassName;
    }

    public void addPublicTestMethod(String publicMethod) {
        publicTestMethods.add(publicMethod);
    }

    public List<String> getPublicTestMethods() {
        return publicTestMethods;
    }
}
