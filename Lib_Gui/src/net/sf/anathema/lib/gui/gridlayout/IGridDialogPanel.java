package net.sf.anathema.lib.gui.gridlayout;

import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.lib.gui.IView;

import javax.swing.JPanel;

public interface IGridDialogPanel extends IView {

  void add(IDialogComponent component);

  @Override
  JPanel getComponent();
}