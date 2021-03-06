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

package com.kitfox.coyote.shape.tessellator2;

import com.kitfox.coyote.shape.bezier.path.cut.Coord;

/**
 *
 * @author kitfox
 */
public class CtrEdge
{
    final CtrVertex v0;
    final CtrVertex v1;
//    boolean forward;
//    CtrEdge peer;
    final CtrHalfEdge left;
    final CtrHalfEdge right;
    int weight = 1;

    public CtrEdge(CtrVertex v0, CtrVertex v1)
    {
        this.v0 = v0;
        this.v1 = v1;
        left = new CtrHalfEdge(this, false);
        right = new CtrHalfEdge(this, true);
    }

    Coord getOppositeCoord(Coord c)
    {
        return v0.coord.equals(c)
                ? v1.coord : v0.coord;
    }
    
    public boolean isPointOnLine(int x, int y)
    {
        Coord c0 = v0.coord;
        Coord c1 = v1.coord;
        int dx0 = c1.x - c0.x;
        int dy0 = c1.y - c0.y;
        int dx1 = x - c0.x;
        int dy1 = y - c0.y;
        return dx0 * dy1 == dy0 * dx1;
    }

    @Override
    public String toString()
    {
        return "[" + v0 + "->" + v1 + "]";
    }

    
}
