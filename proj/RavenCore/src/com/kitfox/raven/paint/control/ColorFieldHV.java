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

import com.kitfox.coyote.math.MathColorUtil;
import com.kitfox.raven.paint.common.RavenPaintColor;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;

/**
 *
 * @author kitfox
 */
public class ColorFieldHV extends ColorModelColorField
{
    private float sat;
//    private float alpha;
    private float[] hsv = new float[3];
    private float[] rgb = new float[3];


    public ColorFieldHV(ColorChooserModel model)
    {
        super(model);
    }

    @Override
    public RavenPaintColor toColor(float x, float y)
    {
        float hue = x;
        float bright = y;

        RavenPaintColor modelColor = model.getColor();
        MathColorUtil.HSVtoRGB(hue, sat, bright, rgb);
        return new RavenPaintColor(rgb[0], rgb[1], rgb[2], modelColor.getA());
    }

    @Override
    public RavenPaintColor toDisplayColor(float x, float y)
    {
        float hue = x;
        float bright = y;

        MathColorUtil.HSVtoRGB(hue, sat, bright, rgb);
        return new RavenPaintColor(rgb[0], rgb[1], rgb[2]);
    }

    @Override
    public Point2D.Float toCoords(RavenPaintColor color)
    {
        if (color == null)
        {
            return new Point2D.Float();
        }
        MathColorUtil.RGBtoHSV(color.r, color.g, color.b, hsv);
        return new Point2D.Float(hsv[0], hsv[2]);
    }

    /**
     * @return the sat
     */
    public float getSat()
    {
        return sat;
    }

    /**
     * @param sat the hue to set
     */
    public void setSat(float sat)
    {
        this.sat = sat;
        fireModelChanged();
    }

//    /**
//     * @return the alpha
//     */
//    public float getAlpha()
//    {
//        return alpha;
//    }
//
//    /**
//     * @param alpha the alpha to set
//     */
//    public void setAlpha(float alpha)
//    {
//        this.alpha = alpha;
//        fireModelChanged();
//    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getSource() == model && ColorChooserModel.PROP_COLOR.equals(evt.getPropertyName()))
        {
            RavenPaintColor color = model.getColor();
            if (color == null)
            {
                color = RavenPaintColor.BLACK;
            }
            MathColorUtil.RGBtoHSV(color.r, color.g, color.b, hsv);
            setSat(hsv[1]);
//            setAlpha(color.a);
        }
    }
}
