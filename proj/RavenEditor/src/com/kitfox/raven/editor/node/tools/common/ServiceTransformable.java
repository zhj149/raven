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
import com.kitfox.coyote.math.CyVector2d;
import com.kitfox.coyote.shape.CyRectangle2d;
import com.kitfox.coyote.shape.CyShape;
import com.kitfox.raven.editor.node.tools.ToolService;
import com.kitfox.raven.util.tree.NodeObject;
import com.kitfox.raven.util.tree.PropertyWrapperFloat;

/**
 *
 * @author kitfox
 */
public interface ServiceTransformable extends ToolService
{

    public NodeObject getNodeObject();

//    @Deprecated
//    public Rectangle2D getBoundsWorld();
//
//    @Deprecated
//    public AffineTransform getLocalToParentTransform(AffineTransform xform);
//
//    @Deprecated
//    public AffineTransform getLocalToWorldTransform(AffineTransform xform);

    public CyMatrix4d getLocalToParentTransform(CyMatrix4d xform);

    public CyMatrix4d getLocalToWorldTransform(CyMatrix4d xform);

    public void setLocalToParentTransform(CyMatrix4d newLocal, CyVector2d pivot, boolean history);

    public PropertyWrapperFloat getTransX();

    public PropertyWrapperFloat getTransY();

    public PropertyWrapperFloat getRotation();

    public PropertyWrapperFloat getSkewAngle();

    public PropertyWrapperFloat getScaleX();

    public PropertyWrapperFloat getScaleY();

    public PropertyWrapperFloat getPivotX();

    public PropertyWrapperFloat getPivotY();

    public CyShape getShapeWorld();

    public CyRectangle2d getBoundsWorld();

//    @Deprecated
//    public Shape getClipShapeWorld();

//    @Deprecated
//    public void setLocalToParentTransform(AffineTransform newLocal, Point2D pivot, boolean history);

}
