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

package com.kitfox.raven.shape.bezier;

/**
 *
 * @author kitfox
 */
@Deprecated
public class PointRecord
{
    final double dist;
    final double t;
    final double px;
    final double py;

    public PointRecord(double dist, double t, double px, double py)
    {
        this.dist = dist;
        this.t = t;
        this.px = px;
        this.py = py;
    }

}
