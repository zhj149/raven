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

package com.kitfox.raven.shape.path;

import java.awt.geom.Path2D;

/**
 *
 * @author kitfox
 */
@Deprecated
public class PathSegQuadTo extends PathSeg
{
    private final int k0x;
    private final int k0y;
    private final int x;
    private final int y;

    public PathSegQuadTo(int k0x, int k0y, int x, int y)
    {
        this.k0x = k0x;
        this.k0y = k0y;
        this.x = x;
        this.y = y;
    }

    @Override
    public void append(Path2D path)
    {
        path.quadTo(k0x, k0y, x, y);
    }

    /**
     * @return the k0x
     */
    public int getK0x() {
        return k0x;
    }

    /**
     * @return the k0y
     */
    public int getK0y() {
        return k0y;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PathSegQuadTo other = (PathSegQuadTo) obj;
        if (this.k0x != other.k0x) {
            return false;
        }
        if (this.k0y != other.k0y) {
            return false;
        }
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.k0x;
        hash = 29 * hash + this.k0y;
        hash = 29 * hash + this.x;
        hash = 29 * hash + this.y;
        return hash;
    }

    @Override
    public String toSVGPath()
    {
        return " Q " + k0x + " " + k0y
            + " " + x + " " + y;
    }
}
