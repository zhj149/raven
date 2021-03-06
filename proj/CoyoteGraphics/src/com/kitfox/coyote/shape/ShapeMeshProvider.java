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

package com.kitfox.coyote.shape;

import com.kitfox.coyote.math.BufferUtil;
import com.kitfox.coyote.math.CyVector2d;
import com.kitfox.coyote.math.d3.BoundingBox3d;
import com.kitfox.coyote.renderer.CyMaterial;
import com.kitfox.coyote.renderer.CyVertexBufferDataProvider;
import com.kitfox.coyote.renderer.CyGLWrapper.BufferUsage;
import com.kitfox.coyote.renderer.CyGLWrapper.DrawMode;
import com.kitfox.coyote.shape.bezier.path.cut.Coord;
import com.kitfox.coyote.shape.tessellator3.PathTessellator3;
import java.lang.ref.SoftReference;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author kitfox
 */
public class ShapeMeshProvider extends CyVertexBufferDataProvider
{
    CyShape shape;
    SoftReference<BufferInfo> bufferInfo;
    double flatnessSquared;

    public ShapeMeshProvider(CyShape shape, double flatnessSquared)
    {
        this.shape = shape;
        this.flatnessSquared = flatnessSquared;
    }

    public ShapeMeshProvider(CyShape shape)
    {
        this(shape, 10000);
    }

    private BufferInfo getBufferInfo()
    {
        BufferInfo info = bufferInfo == null ? null : bufferInfo.get();
        if (info == null)
        {
            info = new BufferInfo();
            info.build();
            bufferInfo = new SoftReference<BufferInfo>(info);
        }
        return info;
    }

    @Override
    public FloatBuffer getVertexData()
    {
        return getBufferInfo().vertBuf;
    }

    @Override
    public ShortBuffer getIndexData()
    {
        return getBufferInfo().indexBuf;
    }

    @Override
    public BufferUsage getVertexUsage()
    {
        return BufferUsage.GL_STATIC_DRAW;
    }

    @Override
    public BufferUsage getIndexUsage()
    {
        return BufferUsage.GL_STATIC_DRAW;
    }

    @Override
    public DrawMode getDrawMode()
    {
        return DrawMode.GL_TRIANGLES;
    }

    @Override
    public int getIndexCount()
    {
        return getBufferInfo().indexBuf.capacity();
    }

    @Override
    public BoundingBox3d getBounds()
    {
        CyRectangle2d bounds = shape.getBounds();
        return new BoundingBox3d(bounds.getX(), bounds.getY(), 0,
                bounds.getWidth(), bounds.getHeight(), 0);
    }

    //-------------------------
    class BufferInfo
    {
        FloatBuffer vertBuf;
        ShortBuffer indexBuf;

        public void build()
        {
            PathTessellator3 tess = new PathTessellator3();
            PathFlattener flat = new PathFlattener(tess, flatnessSquared);

            flat.feedShape(shape);

            ArrayList<Coord> tris = tess.getTrianglesNonZero();
            HashMap<CyVector2d, Integer> vertMap = new HashMap<CyVector2d, Integer>();
            ArrayList<CyVector2d> vertIdx = new ArrayList<CyVector2d>();
            ArrayList<Integer> eleIdx = new ArrayList<Integer>();
            for (Coord c: tris)
            {
                CyVector2d pt = new CyVector2d(c.x, c.y);
                Integer idx = vertMap.get(pt);
                if (idx == null)
                {
                    idx = vertIdx.size();
                    vertIdx.add(pt);
                    vertMap.put(pt, idx);
                }
                eleIdx.add(idx);
            }


            vertBuf = BufferUtil.allocateFloat(vertIdx.size() * 2);
            for (CyVector2d pt: vertIdx)
            {
                vertBuf.put((float)pt.x);
                vertBuf.put((float)pt.y);
            }
            vertBuf.rewind();

            indexBuf = BufferUtil.allocateShort(eleIdx.size());
            for (Integer i: eleIdx)
            {
                indexBuf.put((short)(int)i);
            }
            indexBuf.rewind();

            //Set buffer types
            setVertexArrayInfo(CyMaterial.KEY_POSITION,
                    0 * BufferUtil.SIZEOF_FLOAT, 2);

        }

    }

}
