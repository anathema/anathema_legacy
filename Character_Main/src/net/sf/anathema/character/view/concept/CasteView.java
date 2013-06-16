package net.sf.anathema.character.view.concept;

import net.sf.anathema.lib.gui.selection.IObjectSelectionView;

import javax.swing.ListCellRenderer;

public interface CasteView {

  <V> IObjectSelectionView<V> addObjectSelectionView(String labelText, V[] objects, ListCellRenderer renderer, boolean editable);
}