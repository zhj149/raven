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

package com.kitfox.coyote.shape.bezier.mesh;

import com.kitfox.coyote.shape.bezier.path.cut.Coord;

/**
 *
 * @author kitfox
 */
public class CutSegHalf
{
    final double t0;
    final double t1;
    final Coord c0;
    final Coord c1;
    private final BezierMeshEdge2i edge;
    private final boolean right;
    
    private CutSegHalf peer;

    public CutSegHalf(double t0, double t1, Coord c0, Coord c1, BezierMeshEdge2i edge, boolean right)
    {
        this.t0 = t0;
        this.t1 = t1;
        this.c0 = c0;
        this.c1 = c1;
        this.edge = edge;
        this.right = right;
    }
    
    public int getDx()
    {
        return c1.x - c0.x;
    }

    public int getDy()
    {
        return c1.y - c0.y;
    }

    /**
     * @return the peer
     */
    public CutSegHalf getPeer()
    {
        return peer;
    }

    /**
     * @param peer the peer to set
     */
    public void setPeer(CutSegHalf peer)
    {
        this.peer = peer;
    }

    @Override
    public String toString()
    {
        return "{" + c0 + " " + c1 + " t[" + t0 + " " + t1 + "] " 
                + (right ? "right" : "left") + "}";
    }

    /**
     * @return the parent edge
     */
    public BezierMeshEdge2i getEdge()
    {
        return edge;
    }

    /**
     * @return True if this half edge is the right side of the 
     * parent edge, false if it is the left side.
     */
    public boolean isRight()
    {
        return right;
    }
    
}
