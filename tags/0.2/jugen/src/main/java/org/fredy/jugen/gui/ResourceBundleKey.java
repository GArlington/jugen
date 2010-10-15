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

package org.fredy.jugen.gui;

/**
 * @author fredy
 */
public enum ResourceBundleKey {

    LABEL_APPLICATION("label.application"),
    LABEL_PROJECT_DIR("label.projectdir"),
    LABEL_DEST_DIR("label.destdir"),
    LABEL_TEMPLATE_DIR("label.templatedir"),
    LABEL_EXCLUDE_LIST("label.excludelist"),
    LABEL_JUNIT_VERSION("label.junitversion"),
    LABEL_JUNIT3("label.junit3"),
    LABEL_JUNIT4("label.junit4"),
    LABEL_OVERWRITE("label.overwrite"),
    BUTTON_PROJECT_DIR("button.projectdir"),
    BUTTON_DEST_DIR("button.destdir"),
    BUTTON_TEMPLATE_DIR("button.templatedir"),
    BUTTON_GENERATE("button.generate"),
    ERROR_MANDATORY("error.mandatory");
    
    private String key;

    private ResourceBundleKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }
}
