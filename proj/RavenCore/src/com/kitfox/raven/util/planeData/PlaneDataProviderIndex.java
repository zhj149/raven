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

package com.kitfox.raven.util.planeData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 *
 * @author kitfox
 */
@Deprecated
public final class PlaneDataProviderIndex
{
    private ArrayList<PlaneDataProvider> providerList = new ArrayList<PlaneDataProvider>();
    
    private static PlaneDataProviderIndex instance = new PlaneDataProviderIndex();

    private PlaneDataProviderIndex()
    {
        reload();
    }

    public static PlaneDataProviderIndex inst()
    {
        return instance;
    }

    public void reload()
    {
        reload(PlaneDataProviderIndex.class.getClassLoader());
    }

    public void reload(ClassLoader clsLoader)
    {
        providerList.clear();

        ServiceLoader<PlaneDataProvider> loader = ServiceLoader.load(PlaneDataProvider.class, clsLoader);

        for (Iterator<PlaneDataProvider> it = loader.iterator();
            it.hasNext();)
        {
            PlaneDataProvider fact = it.next();
            providerList.add(fact);
        }
    }

    public ArrayList<PlaneDataProvider> getProviders()
    {
        return new ArrayList<PlaneDataProvider>(providerList);
    }

    public <T extends PlaneDataProvider> PlaneDataProvider getProvider(Class<T> cls)
    {
        for (int i = 0; i < providerList.size(); ++i)
        {
            PlaneDataProvider prov = providerList.get(i);
            if (prov.getClass().equals(cls))
            {
                return prov;
            }
        }
        return null;
    }

    public PlaneDataProvider getProvider(String clazz)
    {
        for (int i = 0; i < providerList.size(); ++i)
        {
            PlaneDataProvider prov = providerList.get(i);
            if (prov.getClass().getCanonicalName().equals(clazz))
            {
                return prov;
            }
        }
        return null;

    }

}
