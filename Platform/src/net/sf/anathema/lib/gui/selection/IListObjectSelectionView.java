package net.sf.anathema.lib.gui.selection;

import net.sf.anathema.lib.gui.AgnosticUIConfiguration;

public interface IListObjectSelectionView<V> extends IVetoableObjectSelectionView<V> {

  void setCellRenderer(AgnosticUIConfiguration<V> renderer);
}