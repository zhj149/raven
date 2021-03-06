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

package com.kitfox.raven.image.importer;

import com.kitfox.raven.util.tree.NodeSymbol;
import com.kitfox.raven.wizard.RavenWizardPageIteratorSimple;
import java.util.Properties;

/**
 *
 * @author kitfox
 */
public class ImageImporterWizard extends RavenWizardPageIteratorSimple
{
    ImageImporterContext ctx;
    ImageImporterPanel panel;

    private ImageImporterWizard(ImageImporterContext ctx, ImageImporterPanel panel)
    {
        super(panel);

        this.ctx = ctx;
        this.panel = panel;
    }

    public static ImageImporterWizard create(NodeSymbol doc, Properties preferences)
    {
        ImageImporterContext ctx = new ImageImporterContext(doc, preferences);
        
        ImageImporterPanel panel = new ImageImporterPanel(ctx);
        return new ImageImporterWizard(ctx, panel);
    }
    
    @Override
    public Object finish()
    {
        ctx.savePreferences();
        ctx.doImport();
        return null;
    }
    
}
