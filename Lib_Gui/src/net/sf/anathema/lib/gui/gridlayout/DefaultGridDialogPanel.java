package net.sf.anathema.lib.gui.gridlayout;

import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogPanelBuilder;
import net.disy.commons.swing.layout.grid.IDialogComponent;

public class DefaultGridDialogPanel implements IGridDialogPanel {

  private final GridDialogPanelBuilder panelBuilder;

  public DefaultGridDialogPanel() {
    panelBuilder = new GridDialogPanelBuilder();
  }

  public void add(IDialogComponent component) {
    panelBuilder.add(component);
  }

  public JPanel getComponent() {
    return panelBuilder.createPanel();
  }
}