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

package com.kitfox.raven.shape.network.pick;

import com.kitfox.coyote.shape.bezier.BezierCurve2d;
import com.kitfox.coyote.shape.bezier.BezierCurve2i;
import com.kitfox.coyote.shape.bezier.mesh.BezierVertexSmooth;
import com.kitfox.raven.paint.RavenPaint;
import com.kitfox.raven.paint.RavenPaintLayout;
import com.kitfox.raven.paint.RavenStroke;
import com.kitfox.raven.shape.network.NetworkDataEdge;

/**
 *
 * @author kitfox
 */
public interface NetworkHandleEdge
{
    public int getIndex();
    public RavenStroke getStroke();
    public RavenPaint getPaint();
    public RavenPaintLayout getPaintLayout();
    public BezierCurve2d getCurveLocal();
    public NetworkHandleVertex getStartVertex();
    public NetworkHandleVertex getEndVertex();

    public boolean isLine();
    public void remove();
    public BezierCurve2i getCurveGraph();
    public NetworkDataEdge getData();
    public BezierVertexSmooth getSmooth0();
    public BezierVertexSmooth getSmooth1();
    public void setSmooth0(BezierVertexSmooth smooth);
    public void setSmooth1(BezierVertexSmooth smooth);
}
