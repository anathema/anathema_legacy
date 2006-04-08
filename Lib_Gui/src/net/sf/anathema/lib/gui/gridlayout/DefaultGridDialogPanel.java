package net.sf.anathema.lib.gui.gridlayout;

import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogPanel;
import net.disy.commons.swing.layout.grid.IDialogComponent;

public class DefaultGridDialogPanel implements IGridDialogPanel {

  private final GridDialogPanel panel;

  public DefaultGridDialogPanel() {
    panel = new GridDialogPanel();
  }

  public DefaultGridDialogPanel(boolean equalWidthColumns) {
    panel = new GridDialogPanel(equalWidthColumns);
  }

  public void add(IDialogComponent component) {
    panel.add(component);
  }

  public void addVerticalSpacing(int verticalSpacing) {
    panel.addVerticalSpacing(verticalSpacing);
  }

  public JPanel getContent() {
    return panel.getContent();
  }
}