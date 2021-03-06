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

import com.kitfox.raven.util.undo.History;
import com.kitfox.raven.util.undo.HistoryAction;
import java.util.ArrayList;

/**
 *
 * @author kitfox
 */
public class ChildWrapperList<NodeType extends NodeObject, ChildType
        extends NodeObject> extends ChildWrapper<NodeType, ChildType>
{
    ArrayList<ChildType> list = new ArrayList<ChildType>();

    public ChildWrapperList(NodeType node, String name, Class<ChildType> childType)
    {
        super(node, name, childType);
    }

    @Override
    public int size()
    {
        return list.size();
    }

    @Override
    public ChildType get(int index)
    {
        return list.get(index);
    }

    public ArrayList<ChildType> getChildren()
    {
        return new ArrayList<ChildType>(list);
    }

    public int indexOf(ChildType child)
    {
        return list.indexOf(child);
    }

    public void add(ChildType child)
    {
        AddChildAction action = new AddChildAction(child);
        doAction(action);
    }

    public void add(int index, ChildType child)
    {
        AddChildAction action = new AddChildAction(index, child);
        doAction(action);
    }

    @Override
    public void set(int index, ChildType child)
    {
        if (index < 0 || index >= size())
        {
            throw new IllegalArgumentException("Index out of bounds");
        }

        History hist = node.getSymbol().getHistory();
        hist.beginTransaction("Set child: " + child.toString());
        remove(index);
        add(index, child);
        hist.commitTransaction();
    }

    public void remove(ChildType child)
    {
        if (child.getParent().getNode() != getNode())
        {
            throw new UnsupportedOperationException("Not a child of this node");
        }

        RemoveChildAction action = new RemoveChildAction(child);
        doAction(action);
    }

    public void remove(int index)
    {
        ChildType child = list.get(index);
        remove(child);
    }

    public void removeAll()
    {
        if (list.isEmpty())
        {
            return;
        }

        beginTransaction("Remove all");
        for (int i = list.size() - 1; i >= 0; --i)
        {
            remove(i);
        }
        commitTransaction();
    }

    //----------------------------------
    public class RemoveChildAction implements HistoryAction
    {
        private final ChildType child;
        int index;

        public RemoveChildAction(ChildType child)
        {
            this.child = child;
        }

        @Override
        public void undo(History history)
        {
            child.setParent(ChildWrapperList.this);
            list.add(index, child);
            fireNodeAdded(index, child);
        }

        @Override
        public void redo(History history)
        {
            index = list.indexOf(child);
            child.setParent(null);
            list.remove(index);
            fireNodeRemoved(index, child);
        }

        @Override
        public String getTitle()
        {
            return "Remove child: " + child.toString();
        }

        /**
         * @return the child
         */
        public ChildType getChild()
        {
            return child;
        }
    }

    public class AddChildAction implements HistoryAction
    {
        final int index;
        final ChildType child;

        public AddChildAction(ChildType child)
        {
            this(list.size(), child);
        }

        public AddChildAction(int index, ChildType child)
        {
            this.index = index;
            this.child = child;
        }

        @Override
        public void undo(History history)
        {
            child.setParent(null);
            list.remove(index);
            fireNodeRemoved(index, child);
        }

        @Override
        public void redo(History history)
        {
            child.setParent(ChildWrapperList.this);
            list.add(index, child);
            fireNodeAdded(index, child);
        }

        @Override
        public String getTitle()
        {
            return "Add child: " + child.toString();
        }
    }

}
