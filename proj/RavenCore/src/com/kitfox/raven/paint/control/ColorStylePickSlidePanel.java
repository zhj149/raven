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
 * ColorStylePickSlidePanel.java
 *
 * Created on Dec 29, 2010, 7:45:56 PM
 */

package com.kitfox.raven.paint.control;

import com.kitfox.raven.paint.common.RavenPaintColor;
import com.kitfox.raven.paint.control.ColorChooserPanel;
import com.kitfox.raven.paint.control.ColorModelColorField;
import com.kitfox.raven.paint.control.ColorModelField1D;
import com.kitfox.raven.paint.control.ColorSliderPanel;
import com.kitfox.raven.paint.control.SimpleColorModel;
import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author kitfox
 */
public class ColorStylePickSlidePanel extends javax.swing.JPanel
        implements PropertyChangeListener
{

    public static final String PROP_COLOR = "color";
    
    SimpleColorModel model;

    ColorModelColorField fieldSlider;
    ColorModelColorField fieldPicker;

    ColorChooserPanel colorChooser = new ColorChooserPanel();
    ColorSliderPanel colorSlider = new ColorSliderPanel();
    
    /** Creates new form ColorStylePickSlidePanel */
    public ColorStylePickSlidePanel(SimpleColorModel model,
            ColorModelColorField fieldPicker,
            ColorModelField1D fieldSlider)
    {
        this.model = model;
        this.fieldPicker = fieldPicker;
        this.fieldSlider = fieldSlider;

        initComponents();

        model.addPropertyChangeListener(this);

        panel_pickArea.add(colorChooser, BorderLayout.CENTER);
        colorChooser.setModel(model);
        colorChooser.setColorField(fieldPicker);


        //Hue slider
        fieldSlider.setHorizontal(true);
        colorSlider.setSliderMargin(16);
        colorSlider.setColorChooserModel(model);
        colorSlider.setColorField(fieldSlider);
        panel_slideArea.add(colorSlider, BorderLayout.CENTER);

    }

    public RavenPaintColor getColor()
    {
        return model.getColor();
    }

    public void setColor(RavenPaintColor color)
    {
        model.setColor(color);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        //Echo color proeprty changes
        firePropertyChange(PROP_COLOR, null, null);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_slideArea = new javax.swing.JPanel();
        panel_pickArea = new javax.swing.JPanel();

        panel_slideArea.setLayout(new java.awt.BorderLayout());

        panel_pickArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel_pickArea.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_slideArea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(panel_pickArea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_pickArea, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_slideArea, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel_pickArea;
    private javax.swing.JPanel panel_slideArea;
    // End of variables declaration//GEN-END:variables

}