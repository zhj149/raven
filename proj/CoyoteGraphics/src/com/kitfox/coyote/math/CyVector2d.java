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

package com.kitfox.coyote.math;

import java.awt.geom.Point2D;

/**
 *
 * @author kitfox
 */
public class CyVector2d
{
    public double x;
    public double y;

    public CyVector2d()
    {
    }

    public CyVector2d(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public CyVector2d(CyVector2d v)
    {
        this.x = v.x;
        this.y = v.y;
    }

    public void set(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public void set(CyVector2d v)
    {
        this.x = v.x;
        this.y = v.y;
    }

    public void setScaled(CyVector2d v, double scalar)
    {
        this.x = v.x * scalar;
        this.y = v.y * scalar;
    }
    
    public double dot(CyVector2d v)
    {
        return x * v.x + y * v.y;
    }

    public double dot(double x, double y)
    {
        return this.x * x + this.y * y;
    }

    /**
     * @return the x
     */
    public double getX()
    {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x)
    {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY()
    {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y)
    {
        this.y = y;
    }

    public void negate()
    {
        x = -x;
        y = -y;
    }

    public void add(CyVector2d v)
    {
        x += v.x;
        y += v.y;
    }

    public void add(double x, double y)
    {
        this.x += x;
        this.y += y;
    }

    public void add(CyVector2d a, CyVector2d b)
    {
        this.x = a.x + b.x;
        this.y = a.y + b.y;
    }

    public void sub(CyVector2d v)
    {
        x -= v.x;
        y -= v.y;
    }

    public void sub(double x, double y)
    {
        this.x -= x;
        this.y -= y;
    }

    public void sub(CyVector2d a, CyVector2d b)
    {
        this.x = a.x - b.x;
        this.y = a.y - b.y;
    }
    
    public void normalize()
    {
        scale(1 / length());
    }

    public void scale(double s)
    {
        x *= s;
        y *= s;
    }

    public double lengthSquared()
    {
        return x * x + y * y;
    }

    public double length()
    {
        return Math.sqrt(lengthSquared());
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final CyVector2d other = (CyVector2d) obj;
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x))
        {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        return hash;
    }

    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }

    public void rotCCW90()
    {
        double tmp = y;
        y = x;
        x = -tmp;
    }

    public Point2D.Double asPoint2D()
    {
        return new Point2D.Double(x, y);
    }

    public double distanceSquared(CyVector2d v)
    {
        return distanceSquared(v.x, v.y);
    }

    public double distanceSquared(double x, double y)
    {
        double dx = this.x - x;
        double dy = this.y - y;
        return dx * dx + dy * dy;
    }

    public double distance(double x, double y)
    {
        return Math.sqrt(distanceSquared(x, y));
    }
    
    public double distance(CyVector2d v)
    {
        return distance(v.x, v.y);
    }

    public CyVector2d projectOnto(CyVector2d v)
    {
        double scalar = dot(v) / v.dot(v);
        return new CyVector2d(v.x * scalar, v.y * scalar);
    }

    public void rotate(double angle)
    {
        double c = Math.cos(angle);
        double s = Math.sin(angle);
        
        double x0 = x * c - y * s;
        double y0 = x * s + y * c;
        
        this.x = x0;
        this.y = y0;
    }

    public void addScaled(CyVector2d v, double s)
    {
        this.x += v.x * s;
        this.y += v.y * s;
    }

    public void subScaled(CyVector2d v, double s)
    {
        this.x -= v.x * s;
        this.y -= v.y * s;
    }

}
