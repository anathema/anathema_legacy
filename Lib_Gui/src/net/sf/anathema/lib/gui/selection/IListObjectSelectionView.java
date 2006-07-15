package net.sf.anathema.lib.gui.selection;

import javax.swing.ListCellRenderer;

public interface IListObjectSelectionView<V> extends IObjectSelectionView<V> {

  public void setCellRenderer(ListCellRenderer renderer);
}