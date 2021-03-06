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

package com.kitfox.raven.editor.node.tools.common.shape.curveEdit;

import com.kitfox.raven.editor.node.tools.common.ToolDisplay;
import java.awt.event.MouseEvent;

/**
 *
 * @author kitfox
 */
abstract public class ToolCurveEditDelegate extends ToolDisplay
{
    protected final ToolCurveEditProvider toolProvider;
    protected final ToolCurveEditDispatch dispatch;
    
    public ToolCurveEditDelegate(ToolCurveEditDispatch dispatch)
    {
        super(dispatch.getUser());
        this.toolProvider = dispatch.toolProvider;
        this.dispatch = dispatch;
        setEnableRestrictAxis(false);
    }

    @Override
    abstract protected void click(MouseEvent evt);
    
    @Override
    abstract protected void startDrag(MouseEvent evt);
    
    @Override
    abstract protected void dragTo(MouseEvent evt);
    
    @Override
    abstract protected void endDrag(MouseEvent evt);
    
}
