package net.sf.anathema.lib.gui.gridlayout;

import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.lib.gui.IView;

public interface IGridDialogPanel extends IView {

  public void add(IDialogComponent component);

  public JPanel getComponent();
}