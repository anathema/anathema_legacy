package net.sf.anathema.lib.gui.gridlayout;

import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.IDialogComponent;

public interface IGridDialogPanel {

  public void add(IDialogComponent component);

  public void addVerticalSpacing(int verticalSpacing);

  public JPanel getContent();
}