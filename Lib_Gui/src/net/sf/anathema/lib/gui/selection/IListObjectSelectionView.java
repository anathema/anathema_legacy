package net.sf.anathema.lib.gui.selection;

import javax.swing.ListCellRenderer;

import net.sf.anathema.lib.gui.list.ListSelectionMode;

public interface IListObjectSelectionView<V> extends IVetoableObjectSelectionView<V> {

  public void setCellRenderer(ListCellRenderer renderer);

  public void setSelectionType(ListSelectionMode type);
}