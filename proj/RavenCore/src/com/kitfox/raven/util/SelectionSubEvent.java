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

package com.kitfox.raven.util;

import java.util.EventObject;

/**
 *
 * @author kitfox
 */
public class SelectionSubEvent<T> extends EventObject
{
    public static final long serialVersionUID = 0;

    private final Object selectionItem;
    private final Class<T> key;
    private final T oldSubselection;
    private final T newSubselection;

    public SelectionSubEvent(Selection source,
            Object selectionItem, Class<T> key,
            T oldSubselection, T newSubselection)
    {
        super(source);
        this.selectionItem = selectionItem;
        this.key = key;
        this.oldSubselection = oldSubselection;
        this.newSubselection = newSubselection;
    }

    /**
     * @return the selectionItem
     */
    public Object getSelectionItem()
    {
        return selectionItem;
    }

    /**
     * @return the oldSubselection
     */
    public T getOldSubselection()
    {
        return oldSubselection;
    }

    /**
     * @return the newSubselection
     */
    public T getNewSubselection()
    {
        return newSubselection;
    }

    /**
     * @return the key
     */
    public Class<T> getKey()
    {
        return key;
    }
    
}
