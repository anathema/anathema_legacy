package net.sf.anathema.lib.gui.selection;

import javax.swing.JComponent;
import javax.swing.ListCellRenderer;

public interface IListObjectSelectionView<V> extends IVetoableObjectSelectionView<V> {

  void setCellRenderer(ListCellRenderer renderer);

  JComponent getComponent();
}