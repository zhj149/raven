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
 * FieldSliderPanel.java
 *
 * Created on Sep 19, 2009, 11:46:20 AM
 */

package com.kitfox.raven.paint.control;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;


/**
 * Combines field strip with a stop model to provide interactive gradient
 * editing.
 * 
 * @author kitfox
 */
abstract public class FieldSliderPanel<StopType> extends javax.swing.JPanel
{
    private static final long serialVersionUID = 0;

    private StopSide side = StopSide.SOUTH;
    private int margin = 5;
    private int sliderMargin = 20;

//    StopSliderPanel<ColorChooserModel> stopSliderPanel = new StopSliderPanel<ColorChooserModel>();
    StopSliderPanel<StopType> stopSliderPanel = new StopSliderPanel<StopType>();
    ColorChooserPanel fieldPanel = new ColorChooserPanel();

    private ColorField colorField;

    /** Creates new form FieldSliderPanel */
    public FieldSliderPanel()
    {
        initComponents();

        fieldPanel.setColorCursor(null);
        stopSliderPanel.setSide(side);
        stopSliderPanel.setMargin(margin);
        panel_color.add(fieldPanel, BorderLayout.CENTER);
        panel_stops.add(stopSliderPanel, BorderLayout.CENTER);
        panel_stops.setPreferredSize(new Dimension(getSliderMargin(), getSliderMargin()));

        updateLayout();
    }

    private void updateLayout()
    {        
        remove(panel_stops);
        
        switch (getSide())
        {
            case NORTH:
                add(panel_stops, BorderLayout.NORTH);
                panel_colorInset.setBorder(BorderFactory.createEmptyBorder(0,getMargin(), 0,getMargin()));
                break;
            case SOUTH:
                add(panel_stops, BorderLayout.SOUTH);
                panel_colorInset.setBorder(BorderFactory.createEmptyBorder(0,getMargin(), 0,getMargin()));
                break;
            case EAST:
                add(panel_stops, BorderLayout.EAST);
                panel_colorInset.setBorder(BorderFactory.createEmptyBorder(getMargin(), 0,getMargin(), 0));
                break;
            case WEST:
                add(panel_stops, BorderLayout.WEST);
                panel_colorInset.setBorder(BorderFactory.createEmptyBorder(getMargin(), 0,getMargin(), 0));
                break;
        }

        revalidate();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_colorInset = new javax.swing.JPanel();
        panel_color = new javax.swing.JPanel();
        panel_stops = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        panel_colorInset.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
        panel_colorInset.setLayout(new java.awt.BorderLayout());

        panel_color.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel_color.setLayout(new java.awt.BorderLayout());
        panel_colorInset.add(panel_color, java.awt.BorderLayout.CENTER);

        add(panel_colorInset, java.awt.BorderLayout.CENTER);

        panel_stops.setLayout(new java.awt.BorderLayout());
        add(panel_stops, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel_color;
    private javax.swing.JPanel panel_colorInset;
    private javax.swing.JPanel panel_stops;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the side
     */
    public StopSide getSide()
    {
        return side;
    }

    /**
     * @param side the side to set
     */
    public void setSide(StopSide side)
    {
        this.side = side;
        stopSliderPanel.setSide(side);
        updateLayout();
    }

    /**
     * @return the margin
     */
    public int getMargin()
    {
        return margin;
    }

    /**
     * @param margin the margin to set
     */
    public void setMargin(int margin)
    {
        this.margin = margin;
        stopSliderPanel.setMargin(margin);
        updateLayout();
    }

    /**
     * @return the colorField
     */
    public ColorField getColorField() {
        return colorField;
    }

    /**
     * @param colorField the colorField to set
     */
    public void setColorField(ColorField colorField)
    {
        this.colorField = colorField;
        stopSliderPanel.setField(colorField);
        fieldPanel.setColorField(colorField);
    }

    /**
     * @return the sliderMargin
     */
    public int getSliderMargin()
    {
        return sliderMargin;
    }

    /**
     * @param sliderMargin the sliderMargin to set
     */
    public void setSliderMargin(int sliderMargin)
    {
        this.sliderMargin = sliderMargin;
        panel_stops.setPreferredSize(new Dimension(getSliderMargin(), getSliderMargin()));
        revalidate();
    }

    public StopModel<StopType> getStopModel()
    {
        return stopSliderPanel.getStopModel();
    }

    public void setStopModel(StopModel<StopType> stopModel)
    {
        stopSliderPanel.setStopModel(stopModel);
    }
}
