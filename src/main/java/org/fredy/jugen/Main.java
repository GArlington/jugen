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

package org.fredy.jugen;

import org.fredy.jugen.cli.JUGenCLI;
import org.fredy.jugen.core.Executable;
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