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

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

import org.fredy.jugen.core.Executable;

/**
 * JUGen GUI.
 * @author fredy
 */
public class JUGenGUI extends JFrame implements Executable {

    private static final long serialVersionUID = 1L;
    private final ResourceBundle rb;
    private FirstPanel firstPanel;
    private SecondPanel secondPanel;
    
    public JUGenGUI() {
        Locale locale = Locale.getDefault();
        rb = ResourceBundle.getBundle("jugen", locale);

        initLookAndFeel();
        initComponents();
        pack();
    }
    
    private void initLookAndFeel() {
        String lookAndFeel = "com.jgoodies.looks.plastic.Plastic3DLookAndFeel";
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(rb.getString(ResourceBundleKey.LABEL_APPLICATION.toString()));
        setLayout(new MigLayout("", "[grow,fill]", "[grow,fill][]"));
        
        firstPanel = new FirstPanel();
        secondPanel = new SecondPanel();
        add(firstPanel, "grow, wrap");
        add(secondPanel);
    }
    
    @Override
    public void run() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                setVisible(true);
            }
        });
    }
    
    class FirstPanel extends JPanel {

        private static final long serialVersionUID = 1L;
        private static final int DEFAULT_NUMROWS = 15; 
        private static final int DEFAULT_NUMCOLS = 30;
        private JTextField projectDirText;
        private JButton projectDirButton;
        private JTextField destDirText;
        private JButton destDirButton;
        private JTextField templateDirText;
        private JButton templateDirButton;
        private JTextArea excludeListTextArea;
        
        public FirstPanel() {
            setLayout(new MigLayout("", "[][grow,fill][]", "[][][][grow,fill][]"));
            setBorder(BorderFactory.createEtchedBorder());
            
            this.add(new JLabel(rb.getString(ResourceBundleKey.LABEL_PROJECT_DIR.toString())));
            projectDirText = new JTextField();
            add(projectDirText, "grow");
            projectDirButton = new JButton(rb.getString(ResourceBundleKey.BUTTON_PROJECT_DIR
                    .toString()));
            add(projectDirButton, "wrap");
            
            add(new JLabel(rb.getString(ResourceBundleKey.LABEL_DEST_DIR.toString())));
            destDirText = new JTextField();
            add(destDirText, "grow");
            destDirButton = new JButton(rb.getString(ResourceBundleKey.BUTTON_DEST_DIR
                    .toString()));
            add(destDirButton, "wrap");
            
            add(new JLabel(rb.getString(ResourceBundleKey.LABEL_TEMPLATE_DIR.toString())));
            templateDirText = new JTextField();
            add(templateDirText, "grow");
            templateDirButton = new JButton(rb.getString(ResourceBundleKey.BUTTON_TEMPLATE_DIR
                    .toString()));
            add(templateDirButton, "wrap");

            add(new JLabel(rb.getString(ResourceBundleKey.LABEL_EXCLUDE_LIST.toString())));
            
            excludeListTextArea = new JTextArea();
            excludeListTextArea.setBorder(BorderFactory.createEtchedBorder());
            excludeListTextArea.setRows(DEFAULT_NUMROWS);
            excludeListTextArea.setColumns(DEFAULT_NUMCOLS);
            add(new JScrollPane(excludeListTextArea), "span, grow, wrap");
        }
        
        public void enableComponents(boolean enabled) {
            projectDirText.setEnabled(enabled);
            projectDirButton.setEnabled(enabled);
            destDirText.setEnabled(enabled);
            destDirButton.setEnabled(enabled);
            templateDirText.setEnabled(enabled);
            templateDirButton.setEnabled(enabled);
            excludeListTextArea.setEnabled(enabled);
        }
    }
    
    class SecondPanel extends JPanel {

        private static final long serialVersionUID = 1L;
        private JButton generateButton;
        
        public SecondPanel() {
            setLayout(new MigLayout("", "[grow,fill]", ""));
            setBorder(BorderFactory.createEtchedBorder());
            
            generateButton = new JButton(rb.getString(ResourceBundleKey.BUTTON_GENERATE.toString()));
            add(generateButton);
        }
        
        public void enableComponents(boolean enabled) {
            generateButton.setEnabled(enabled);
        }
    }
}