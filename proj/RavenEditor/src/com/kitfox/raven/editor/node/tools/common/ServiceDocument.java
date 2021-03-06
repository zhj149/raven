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

package com.kitfox.raven.editor.node.tools.common;

import com.kitfox.coyote.math.CyMatrix4d;
import com.kitfox.coyote.shape.CyRectangle2d;
import com.kitfox.raven.editor.node.scene.snap.GraphLayout;
import com.kitfox.raven.editor.node.scene.snap.Snapping;
import com.kitfox.raven.util.Intersection;
import com.kitfox.raven.editor.node.tools.ToolService;
import com.kitfox.raven.util.Selection;
import com.kitfox.raven.util.tree.NodeSymbol;
import com.kitfox.raven.util.tree.NodeObject;
import java.util.ArrayList;

/**
 *
 * @author kitfox
 */
public interface ServiceDocument extends ToolService
{
    public NodeSymbol getSymbol();

    /**
     * Pick objects in selection region.
     * 
     * @param rectangle Selection window defined in device window space
     * @param worldToPick Transform from device space to world space
     * @param intersection Type of intersection for determining hits
     * @return Best object that fits pick.  Implementing class is
     * free to decide what 'best' means.
     */
    public NodeObject pickObject(CyRectangle2d rectangle, CyMatrix4d worldToPick, Intersection intersection);

    public void pickObjects(CyRectangle2d rectangle, CyMatrix4d worldToPick, Intersection intersection, ArrayList<NodeObject> pickList);

    public Selection<NodeObject> getSelection();

//    public Snapping getSnapping();
    
//    public GraphLayout getGraphLayout();
}
