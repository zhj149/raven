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

package com.kitfox.raven.util.tree;

/**
 *
 * @author kitfox
 */
public class PropertyWrapperFloat<NodeType extends NodeObject>
        extends PropertyWrapperNumeric<NodeType, Float>
{
    public PropertyWrapperFloat(
            NodeType node,
            String name)
    {
        super(node, name, Float.class, new PropertyDataInline<Float>(0f));
    }

    public PropertyWrapperFloat(
            NodeType node,
            String name,
            Integer initialValue)
    {
        super(node, name, Float.class, new PropertyDataInline<Float>((float)(int)initialValue));
    }

    public PropertyWrapperFloat(
            NodeType node,
            String name,
            Float initialValue)
    {
        super(node, name, Float.class, new PropertyDataInline<Float>(initialValue));
    }

    public PropertyWrapperFloat(
            NodeType node,
            String name,
            PropertyData<Float> initialValue)
    {
        super(node, name, Float.class, initialValue);
    }

    public PropertyWrapperFloat(
            NodeType node,
            String name,
            int flags,
            Integer initialValue)
    {
        super(node, name, flags, Float.class, new PropertyDataInline<Float>((float)(int)initialValue));
    }

    public PropertyWrapperFloat(
            NodeType node,
            String name,
            int flags,
            Float initialValue)
    {
        super(node, name, flags, Float.class, new PropertyDataInline<Float>(initialValue));
    }

    public PropertyWrapperFloat(
            NodeType node,
            String name,
            int flags,
            PropertyData<Float> initialValue)
    {
        super(node, name, flags, Float.class, initialValue);
    }

    @Override
    public double getValueNumeric()
    {
        return getValue();
    }

    @Override
    public void setValueNumeric(double value)
    {
        setValue((float)value);
    }
}
