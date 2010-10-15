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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

import org.fredy.jugen.core.Executable;
import org.fredy.jugen.core.JUGen;
import org.fredy.jugen.core.JUGenException;
import org.fredy.jugen.core.JUGenParam;
import org.fredy.jugen.core.Template;

/**
 * JUGen GUI.
 * @author fredy
 */
public class JUGenGUI extends JFrame implements Executable {

    private static final long serialVersionUID = 1L;
    private final ResourceBundle rb;
    private FirstPanel firstPanel;
    private SecondPanel secondPanel;
    private JUGen jugen = new JUGen();
    private static final String ERROR_TITLE = "Error";
    
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
        
        firstPanel = new FirstPanel(this);
        secondPanel = new SecondPanel(this);
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
        private static final int DEFAULT_NUMCOLS = 50;
        private JTextField projectDirText;
        private JButton projectDirButton;
        private JTextField destDirText;
        private JButton destDirButton;
        private JTextField templateDirText;
        private JButton templateDirButton;
        private JRadioButton junit3RadioButton;
        private JRadioButton junit4RadioButton;
        private JCheckBox overwriteCheckBox;
        private JTextArea excludeListTextArea;
        private JFileChooser fileChooser;
        private File projectDir;
        private File destDir;
        private File templateDir;
        
        public FirstPanel(final JUGenGUI gui) {
            setLayout(new MigLayout("", "[][grow,fill][]", "[][][][][grow,fill][]"));
            setBorder(BorderFactory.createEtchedBorder());
            
            this.add(new JLabel(rb.getString(ResourceBundleKey.LABEL_PROJECT_DIR.toString())));
            projectDirText = new JTextField();
            projectDirText.setEnabled(false);
            add(projectDirText, "grow");
            projectDirButton = new JButton(rb.getString(ResourceBundleKey.BUTTON_PROJECT_DIR
                    .toString()));
            fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            projectDirButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int result = fileChooser.showOpenDialog(gui);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        projectDir = fileChooser.getSelectedFile();
                        projectDirText.setText(projectDir.toString());
                        gui.pack();
                    }
                }
            });
            add(projectDirButton, "wrap");
            
            add(new JLabel(rb.getString(ResourceBundleKey.LABEL_DEST_DIR.toString())));
            destDirText = new JTextField();
            destDirText.setEnabled(false);
            add(destDirText, "grow");
            destDirButton = new JButton(rb.getString(ResourceBundleKey.BUTTON_DEST_DIR
                    .toString()));
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            destDirButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int result = fileChooser.showOpenDialog(gui);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        destDir = fileChooser.getSelectedFile();
                        destDirText.setText(destDir.toString());
                        gui.pack();
                    }
                }
            });
            add(destDirButton, "wrap");
            
            add(new JLabel(rb.getString(ResourceBundleKey.LABEL_TEMPLATE_DIR.toString())));
            templateDirText = new JTextField();
            templateDirText.setEnabled(false);
            add(templateDirText, "grow");
            templateDirButton = new JButton(rb.getString(ResourceBundleKey.BUTTON_TEMPLATE_DIR
                    .toString()));
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            templateDirButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int result = fileChooser.showOpenDialog(gui);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        templateDir = fileChooser.getSelectedFile();
                        templateDirText.setText(templateDir.toString());
                        gui.pack();
                    }
                }
            });
            add(templateDirButton, "wrap");

            add(new JLabel(rb.getString(ResourceBundleKey.LABEL_JUNIT_VERSION.toString())));
            junit3RadioButton = new JRadioButton(rb.getString(ResourceBundleKey.LABEL_JUNIT3
                    .toString()));
            junit4RadioButton = new JRadioButton(rb.getString(ResourceBundleKey.LABEL_JUNIT4
                    .toString()));
            junit4RadioButton.setSelected(true);
            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(junit3RadioButton);
            buttonGroup.add(junit4RadioButton);
            JPanel radioButtonPanel = new JPanel(new MigLayout());
            radioButtonPanel.add(junit3RadioButton);
            radioButtonPanel.add(junit4RadioButton);
            add(radioButtonPanel, "span, wrap");
            
            add(new JLabel(rb.getString(ResourceBundleKey.LABEL_EXCLUDE_LIST.toString())));
            excludeListTextArea = new JTextArea();
            excludeListTextArea.setBorder(BorderFactory.createEtchedBorder());
            excludeListTextArea.setRows(DEFAULT_NUMROWS);
            excludeListTextArea.setColumns(DEFAULT_NUMCOLS);
            add(new JScrollPane(excludeListTextArea), "span, grow, wrap");
            
            add(new JLabel(rb.getString(ResourceBundleKey.LABEL_OVERWRITE.toString())));
            overwriteCheckBox = new JCheckBox();
            JPanel overwritePanel = new JPanel(new MigLayout());
            overwritePanel.add(overwriteCheckBox);
            add(overwritePanel, "span, wrap");
        }
        
        public void enableComponents(boolean enabled) {
            projectDirButton.setEnabled(enabled);
            destDirButton.setEnabled(enabled);
            templateDirButton.setEnabled(enabled);
            excludeListTextArea.setEnabled(enabled);
            junit3RadioButton.setEnabled(enabled);
            junit4RadioButton.setEnabled(enabled);
        }

        public File getProjectDir() {
            return projectDir;
        }

        public File getDestDir() {
            return destDir;
        }

        public File getTemplateDir() {
            return templateDir;
        }

        public boolean isJunit4Selected() {
            return junit4RadioButton.isSelected();
        }

        public boolean isJunit3Selected() {
            return junit3RadioButton.isSelected();
        }
        
        public boolean isOverwriteSelected() {
            return overwriteCheckBox.isSelected();
        }
        
        public List<String> getExcludeList() {
            return Arrays.asList(excludeListTextArea.getText().split(","));
        }
    }
    
    class SecondPanel extends JPanel {

        private static final long serialVersionUID = 1L;
        private JButton generateButton;
        
        public SecondPanel(final JUGenGUI gui) {
            setLayout(new MigLayout("", "[grow,fill]", ""));
            setBorder(BorderFactory.createEtchedBorder());
            
            generateButton = new JButton(rb.getString(ResourceBundleKey.BUTTON_GENERATE.toString()));
            generateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!validateInput()) {
                        JOptionPane.showMessageDialog(gui, rb
                                .getString(ResourceBundleKey.ERROR_MANDATORY.toString()),
                                ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    final JUGenParam param = new JUGenParam();
                    param.setFile(firstPanel.getProjectDir());
                    param.setDestDir(firstPanel.getDestDir());
                    param.setTemplateDir(firstPanel.getTemplateDir());
                    param.setOverwrite(firstPanel.isOverwriteSelected());
                    param.setExcludeList(firstPanel.getExcludeList());
                    if (firstPanel.isJunit3Selected()) {
                        param.setTemplate(Template.JUNIT3);
                    } else if (firstPanel.isJunit4Selected()) {
                        param.setTemplate(Template.JUNIT4);
                    } else {
                        throw new JUGenException("Invalid JUnit version");
                    }
                    firstPanel.enableComponents(false);
                    enableComponents(false);
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            try {
                                jugen.generateJUnit(param);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return null;
                        }
                        
                        @Override
                        protected void done() {
                            firstPanel.enableComponents(true);
                            enableComponents(true);
                        }
                    };
                    worker.execute();
                }
            });
            add(generateButton);
        }
        
        public void enableComponents(boolean enabled) {
            generateButton.setEnabled(enabled);
        }
        
        private boolean validateInput() {
            if (firstPanel.getProjectDir() == null || firstPanel.getDestDir() == null ||
                    firstPanel.getTemplateDir() == null) {
                return false;
            }
            return true;
        }
    }
}