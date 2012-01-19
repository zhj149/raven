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

package com.kitfox.raven.paint.control;

import com.kitfox.raven.paint.common.RavenPaintTexture;
import com.kitfox.raven.util.FileFilterSuffix;
import com.kitfox.raven.util.resource.ResourceCache;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 *
 * @author kitfox
 */
public class TextureEditorPanel extends javax.swing.JPanel
{
    public static final String PROP_TEXTURE = "texture";
    private RavenPaintTexture texture;

    ImageChip imageChip = new ImageChip();
    
    JFileChooser fileChooser = new JFileChooser();
    
    /**
     * Creates new form TextureEditorPanel
     */
    public TextureEditorPanel()
    {
        initComponents();
        panel_display.add(imageChip, BorderLayout.CENTER);
        
        FileFilterSuffix filter = new FileFilterSuffix(
                "Image file", ImageIO.getReaderFileSuffixes());
        fileChooser.setFileFilter(filter);
        
        update();
    }

    private void update()
    {
        if (texture == null)
        {
            text_uri.setText("");
            imageChip.setImage(null);
            return;
        }
        
        URI uri = texture.getTexture();
        text_uri.setText(uri.toASCIIString());
        Object res = ResourceCache.inst().getResource(uri);
        if (res instanceof BufferedImage)
        {
            imageChip.setImage((BufferedImage)res);
        }
    }
    
    public void updateFromText()
    {
        String text = text_uri.getText();
        URI uri = null;
        try
        {
            uri = new URI(text);
        } catch (URISyntaxException ex)
        {
//            Logger.getLogger(TextureEditorPanel.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        RavenPaintTexture oldTexture = texture;
        texture = new RavenPaintTexture(uri);
        update();
        firePropertyChange(PROP_TEXTURE, oldTexture, texture);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        text_uri = new javax.swing.JTextField();
        bn_uriBrowse = new javax.swing.JButton();
        panel_display = new javax.swing.JPanel();

        jLabel1.setText("URI");

        text_uri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_uriActionPerformed(evt);
            }
        });
        text_uri.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                text_uriFocusLost(evt);
            }
        });

        bn_uriBrowse.setText("...");
        bn_uriBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bn_uriBrowseActionPerformed(evt);
            }
        });

        panel_display.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel_display.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_display, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_uri, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bn_uriBrowse)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(text_uri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bn_uriBrowse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_display, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bn_uriBrowseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_bn_uriBrowseActionPerformed
    {//GEN-HEADEREND:event_bn_uriBrowseActionPerformed
        if (texture != null)
        {
            URI uri = texture.getTexture();
            File curDir = new File(uri);
            fileChooser.setCurrentDirectory(curDir);
        }
        
        if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
        {
            return;
        }
        
        File file = fileChooser.getSelectedFile();
        setTexture(new RavenPaintTexture(file.toURI()));
    }//GEN-LAST:event_bn_uriBrowseActionPerformed

    private void text_uriActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_text_uriActionPerformed
    {//GEN-HEADEREND:event_text_uriActionPerformed
        updateFromText();
    }//GEN-LAST:event_text_uriActionPerformed

    private void text_uriFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_text_uriFocusLost
    {//GEN-HEADEREND:event_text_uriFocusLost
        updateFromText();
    }//GEN-LAST:event_text_uriFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bn_uriBrowse;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panel_display;
    private javax.swing.JTextField text_uri;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the texture
     */
    public RavenPaintTexture getTexture()
    {
        return texture;
    }

    /**
     * @param texture the texture to set
     */
    public void setTexture(RavenPaintTexture texture)
    {
        RavenPaintTexture oldTexture = this.texture;
        this.texture = texture;
        update();
        firePropertyChange(PROP_TEXTURE, oldTexture, texture);
    }
    
    //----------------------------------
    class ImageChip extends JPanel
    {
        private BufferedImage image;

        @Override
        protected void paintComponent(Graphics g)
        {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
            
            if (image != null)
            {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            }
        }

        /**
         * @return the image
         */
        public BufferedImage getImage()
        {
            return image;
        }

        /**
         * @param image the image to set
         */
        public void setImage(BufferedImage image)
        {
            this.image = image;
            
            repaint();
        }
        
    }
}
