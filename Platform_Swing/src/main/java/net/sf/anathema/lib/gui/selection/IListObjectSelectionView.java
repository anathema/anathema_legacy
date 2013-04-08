package net.sf.anathema.lib.gui.selection;

import net.sf.anathema.lib.gui.TechnologyAgnosticUIConfiguration;

import javax.swing.JComponent;

public interface IListObjectSelectionView<V> extends IVetoableObjectSelectionView<V> {

  void setCellRenderer(TechnologyAgnosticUIConfiguration<V> renderer);

  JComponent getComponent();
}