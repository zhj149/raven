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

package com.kitfox.coyote.renderer;

import com.kitfox.coyote.renderer.CyGLContext.FramebufferInfo;
import com.kitfox.coyote.renderer.CyGLWrapper.FramebufferStatus;

/**
 *
 * @author kitfox
 */
public class CyFramebuffer
{
    CyFramebufferAttachment[] attachments;

    int dirty;
    private final int width;
    private final int height;

    public CyFramebuffer(int width, int height, CyFramebufferAttachment... attachments)
    {
        this.width = width;
        this.height = height;
        this.attachments = attachments;
    }
    
    public void bind(CyGLContext ctx, CyGLWrapper gl)
    {
        FramebufferInfo info = ctx.getFramebufferInfo(this, gl);
        int fboId = info.getFboId();

        gl.glBindFramebuffer(fboId);
        gl.glViewport(0, 0, width, height);
        if (info.getDirty() < dirty)
        {
            for (int i = 0; i < attachments.length; ++i)
            {
                CyFramebufferAttachment attach = attachments[i];
                attach.bindFramebuffer(ctx, gl);
            }

            FramebufferStatus status = gl.glCheckFramebufferStatus();
            if (status != FramebufferStatus.GL_FRAMEBUFFER_COMPLETE)
            {
                throw new RuntimeException("Framebuffer incomplete: " + status);
            }

            info.setDirty(dirty);
        }
    }

    /**
     * Replace FBO's current attachments with new set.  Will
     * have side effect of binding the FBO.
     * 
     * @param ctx
     * @param gl
     * @param attachments 
     */
    public void setAttachments(CyGLContext ctx, CyGLWrapper gl,
            CyFramebufferAttachment... attachments)
    {
        FramebufferInfo info = ctx.getFramebufferInfo(this, gl);
        int fboId = info.getFboId();

        gl.glBindFramebuffer(fboId);

        for (int i = 0; i < this.attachments.length; ++i)
        {
            CyFramebufferAttachment attach = attachments[i];
            attach.unbindFramebuffer(ctx, gl);
        }
        
        this.attachments = attachments;

        for (int i = 0; i < attachments.length; ++i)
        {
            CyFramebufferAttachment attach = attachments[i];
            attach.bindFramebuffer(ctx, gl);
        }

        FramebufferStatus status = gl.glCheckFramebufferStatus();
        if (status != FramebufferStatus.GL_FRAMEBUFFER_COMPLETE)
        {
            throw new RuntimeException("Framebuffer incomplete: " + status);
        }
    }

    /**
     * @return the width
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight()
    {
        return height;
    }

}
