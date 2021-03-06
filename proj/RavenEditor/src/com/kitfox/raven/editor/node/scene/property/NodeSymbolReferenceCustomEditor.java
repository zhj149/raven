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

package com.kitfox.raven.editor.node.scene.property;

import com.kitfox.raven.util.tree.NodeSymbol;
import com.kitfox.raven.util.tree.NodeSymbolProvider;
import com.kitfox.raven.util.tree.NodeSymbolProviderIndex;
import com.kitfox.raven.util.tree.PropertyCustomEditor;
import com.kitfox.raven.util.tree.PropertyData;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author kitfox
 */
public class NodeSymbolReferenceCustomEditor 
        extends javax.swing.JPanel
        implements PropertyCustomEditor
{
    final NodeSymbolReferenceEditor editor;
//    boolean updating;

    PropertyData<NodeSymbolReference> initValue;
    PropertyData<NodeSymbolReference> curValue;

    boolean updating;

    /**
     * Creates new form NodeSymbolReferenceCustomEditor
     */
    public NodeSymbolReferenceCustomEditor(NodeSymbolReferenceEditor editor)
    {
        this.editor = editor;
        curValue = initValue = editor.getValue();
        
        initComponents();
        list_symbols.setCellRenderer(new Renderer());

        updateFromEditorSwing();
    }

    private void updateFromEditorSwing()
    {
        updating = true;

        NodeSymbol sym = editor.getWrapper().getNode().getSymbol();
        ArrayList<NodeSymbol> list = sym.getDocument().getSymbols();
        //Collections.sort(list);
        
        list_symbols.setListData(list.toArray());

        //NodeDocument doc = sym.getDocument();
        NodeSymbolReference ref = curValue.getValue(sym);
        NodeSymbol curSym = ref == null ? null
                : sym.getDocument().getSymbol(ref.getUid());
                
        if (curSym != null)
        {
            list_symbols.setSelectedValue(curSym, true);
        }
        
        updating = false;
    }

    @Override
    public Component getCustomEditor()
    {
        return this;
    }

    @Override
    public void customEditorCommit()
    {
        NodeSymbol sym = (NodeSymbol)list_symbols.getSelectedValue();
        
        NodeSymbolReference ref = 
                new NodeSymbolReference(sym.getSymbolUid());
        editor.setValue(ref);
    }

    @Override
    public void customEditorCancel()
    {
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        list_symbols = new javax.swing.JList();

        list_symbols.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                list_symbolsValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(list_symbols);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void list_symbolsValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_list_symbolsValueChanged
    {//GEN-HEADEREND:event_list_symbolsValueChanged
        
    }//GEN-LAST:event_list_symbolsValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList list_symbols;
    // End of variables declaration//GEN-END:variables

    //------------------------------------
    
    class Renderer extends JLabel implements ListCellRenderer
    {
        private static final long serialVersionUID = 1;

        public Renderer()
        {
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index, 
                boolean isSelected, boolean cellHasFocus)
        {
            if (isSelected)
            {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            }
            else
            {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            
            NodeSymbol sym = (NodeSymbol)value;
            String name = sym.getName();
            setText(name == null ? "*" : name);
            
            NodeSymbolProvider prov = 
                    NodeSymbolProviderIndex.inst().getProvider(sym.getClass());
            setIcon(prov == null ? null : prov.getIcon());
            return this;
        }
    }
}
