/*
 * Copyright 2011 Mark McKay
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * ImportSWFPanel.java
 *
 * Created on Dec 5, 2010, 9:39:23 AM
 */

package com.kitfox.raven.swf.importer;

import com.kitfox.raven.util.FileFilterSuffix;
import com.kitfox.raven.wizard.RavenWizardPage;
import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 *
 * @author kitfox
 */
public class SWFImporterPanel extends JPanel
        implements RavenWizardPage
{
//    public static final String WIZ_ID = "import";
    public static final String WIZ_DESC = "Choose SWF import settings";

    public static final String PREF_IMPORT_BG_COL = "importBackgroundColor";
    public static final String PREF_FILE = "file";
    
    FileChooser fileChooser = new FileChooser();
    final SWFImporterContext ctx;

    boolean updating;

    /** Creates new form ImportSWFPanel */
    public SWFImporterPanel(SWFImporterContext ctx)
    {
        this.ctx = ctx;
        initComponents();

        updateFromProperties();
    }

    private void updateFromProperties()
    {
        updating = true;

        text_file.setText(ctx.getFile());
        check_importBgColor.setSelected(ctx.isUseBackground());

        File file = new File(ctx.getFile());
        if (file.exists())
        {
            if (!file.isDirectory())
            {
                file = file.getParentFile();
            }
            fileChooser.setCurrentDirectory(file);
        }

        updating = false;
    }

    @Override
    public String getTitle()
    {
        return WIZ_DESC;
    }

    @Override
    public Component getComponent()
    {
        return this;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jLabel1 = new javax.swing.JLabel();
        text_file = new javax.swing.JTextField();
        bn_chooseFile = new javax.swing.JButton();
        check_importBgColor = new javax.swing.JCheckBox();

        jLabel1.setText("Location");

        text_file.setName("location"); // NOI18N
        text_file.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                text_fileActionPerformed(evt);
            }
        });

        bn_chooseFile.setText("...");
        bn_chooseFile.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bn_chooseFileActionPerformed(evt);
            }
        });

        check_importBgColor.setText("Import background color");
        check_importBgColor.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                check_importBgColorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_file, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bn_chooseFile))
                    .addComponent(check_importBgColor))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(text_file, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bn_chooseFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(check_importBgColor)
                .addContainerGap(236, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bn_chooseFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bn_chooseFileActionPerformed
        int val = fileChooser.showOpenDialog(this);
        if (val != JFileChooser.APPROVE_OPTION)
        {
            return;
        }

        File file = fileChooser.getSelectedFile();
        String name = file.getAbsolutePath();
        text_file.setText(name);
        ctx.setFile(name);
    }//GEN-LAST:event_bn_chooseFileActionPerformed

    private void check_importBgColorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_check_importBgColorActionPerformed
    {//GEN-HEADEREND:event_check_importBgColorActionPerformed
        if (updating)
        {
            return;
        }

        ctx.setUseBackground(check_importBgColor.isSelected());
    }//GEN-LAST:event_check_importBgColorActionPerformed

    private void text_fileActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_text_fileActionPerformed
    {//GEN-HEADEREND:event_text_fileActionPerformed
        if (updating)
        {
            return;
        }

        ctx.setFile(text_file.getText());
    }//GEN-LAST:event_text_fileActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bn_chooseFile;
    private javax.swing.JCheckBox check_importBgColor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField text_file;
    // End of variables declaration//GEN-END:variables



    class FileChooser extends JFileChooser
    {
        public FileChooser()
        {
            FileFilterSuffix filter = new FileFilterSuffix(
                    "Shockwave Flash", "swf");
            
            setFileFilter(filter);
        }
    }
}
