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
 * DocumentPanel.java
 *
 * Created on Oct 6, 2010, 4:02:04 PM
 */

package com.kitfox.rabbit.parser;

import com.kitfox.rabbit.nodes.RaSvg;
import com.kitfox.rabbit.render.awt.RabbitRendererAWT;
import com.kitfox.rabbit.render.awt.Surface2DAwt;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author kitfox
 */
public class RabbitDocumentPanel extends javax.swing.JPanel
{
    protected RabbitDocument document;
    public static final String PROP_DOCUMENT = "document";

    /** Creates new form DocumentPanel */
    public RabbitDocumentPanel()
    {
        initComponents();
    }

    @Override
    protected void paintComponent(Graphics gg)
    {
        super.paintComponent(gg);

        if (document == null)
        {
            return;
        }

        Graphics2D g = (Graphics2D)gg;
        
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Surface2DAwt surf = new Surface2DAwt(g, getBounds());
        RabbitRendererAWT renderer = new RabbitRendererAWT(document.getUniverse(), surf);

        RaSvg root = document.getRootNode();
        root.render(renderer);

    }



    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    /**
     * Get the value of document
     *
     * @return the value of document
     */
    public RabbitDocument getDocument() {
        return document;
    }

    /**
     * Set the value of document
     *
     * @param document new value of document
     */
    public void setDocument(RabbitDocument document) {
        RabbitDocument oldDocument = this.document;
        this.document = document;
        firePropertyChange(PROP_DOCUMENT, oldDocument, document);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
