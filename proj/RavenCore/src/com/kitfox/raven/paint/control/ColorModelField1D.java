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

package com.kitfox.raven.paint.control;

/**
 *
 * @author kitfox
 */
abstract public class ColorModelField1D extends ColorModelColorField
{
    private boolean horizontal;

    public ColorModelField1D(ColorChooserModel model)
    {
        super(model);
    }

    /**
     * @return the horizontal
     */
    public boolean isHorizontal()
    {
        return horizontal;
    }

    /**
     * @param horizontal the horizontal to set
     */
    public void setHorizontal(boolean horizontal)
    {
        this.horizontal = horizontal;
        fireModelChanged();
    }


}
