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
 * RavenWizardDialog.java
 *
 * Created on Feb 20, 2011, 5:00:12 PM
 */

package com.kitfox.raven.wizard;

import com.kitfox.raven.util.tree.NodeSymbol;
import java.awt.BorderLayout;
import java.awt.Window;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author kitfox
 */
public class RavenWizardDialog extends javax.swing.JDialog
        implements RavenWizardPageListener
{
    RavenWizardPageIterator<NodeSymbol> pageIt;
    RavenWizardPage curPage;

    private NodeSymbol nodeSymbol;

    /** Creates new form RavenWizardDialog */
    public RavenWizardDialog(Window parent, RavenWizardPageIterator<NodeSymbol> pageIt)
    {
        super(parent, "", DEFAULT_MODALITY_TYPE);
        initComponents();

        this.pageIt = pageIt;
        pageIt.addRavenWizardPageListener(this);
        nextPage();

        pack();
    }

    private void nextPage()
    {
        curPage = pageIt.nextPage();
        setTitle(curPage.getTitle());

        panel_displayArea.removeAll();
        panel_displayArea.add(curPage.getComponent(), BorderLayout.CENTER);
        panel_displayArea.revalidate();
        panel_displayArea.repaint();

        updateButtons();
    }

    private void prevPage()
    {
        curPage = pageIt.prevPage();
        setTitle(curPage.getTitle());

        panel_displayArea.removeAll();
        panel_displayArea.add(curPage.getComponent(), BorderLayout.CENTER);
        panel_displayArea.revalidate();
        panel_displayArea.repaint();

        updateButtons();
    }

    private void updateButtons()
    {
        bn_back.setEnabled(pageIt.isPrevPageAvailable());
        bn_next.setEnabled(pageIt.isNextPageAvailable());
        bn_finish.setEnabled(pageIt.isFinishAvailable());
        bn_cancel.setEnabled(pageIt.isCancelAvailable());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_displayArea = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        bn_back = new javax.swing.JButton();
        bn_next = new javax.swing.JButton();
        bn_finish = new javax.swing.JButton();
        bn_cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panel_displayArea.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panel_displayArea, java.awt.BorderLayout.CENTER);

        bn_back.setText("< Back");
        bn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bn_backActionPerformed(evt);
            }
        });
        jPanel2.add(bn_back);

        bn_next.setText("Next >");
        bn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bn_nextActionPerformed(evt);
            }
        });
        jPanel2.add(bn_next);

        bn_finish.setText("Finish");
        bn_finish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bn_finishActionPerformed(evt);
            }
        });
        jPanel2.add(bn_finish);

        bn_cancel.setText("Cancel");
        bn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bn_cancelActionPerformed(evt);
            }
        });
        jPanel2.add(bn_cancel);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bn_backActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_bn_backActionPerformed
    {//GEN-HEADEREND:event_bn_backActionPerformed
        prevPage();
    }//GEN-LAST:event_bn_backActionPerformed

    private void bn_nextActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_bn_nextActionPerformed
    {//GEN-HEADEREND:event_bn_nextActionPerformed
        nextPage();
    }//GEN-LAST:event_bn_nextActionPerformed

    private void bn_finishActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_bn_finishActionPerformed
    {//GEN-HEADEREND:event_bn_finishActionPerformed
        nodeSymbol = pageIt.finish();
        setVisible(false);
        dispose();
    }//GEN-LAST:event_bn_finishActionPerformed

    private void bn_cancelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_bn_cancelActionPerformed
    {//GEN-HEADEREND:event_bn_cancelActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_bn_cancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bn_back;
    private javax.swing.JButton bn_cancel;
    private javax.swing.JButton bn_finish;
    private javax.swing.JButton bn_next;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panel_displayArea;
    // End of variables declaration//GEN-END:variables

    @Override
    public void wizardPageNavigationChanged(ChangeEvent evt)
    {
        updateButtons();
    }

    /**
     * @return the nodeDocument
     */
    public NodeSymbol getNodeSymbol()
    {
        return nodeSymbol;
    }

}
