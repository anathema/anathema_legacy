package net.sf.anathema.lib.gui.layout;

import java.awt.Component;

import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.disy.commons.swing.layout.grid.IDialogComponent;

public class SingleOverallComponent implements IDialogComponent {

  private final Component component;

  public SingleOverallComponent(Component component) {
    this.component = component;
  }

  public int getColumnCount() {
    return 1;
  }

  public void fillInto(JPanel panel, int columnCount) {
    panel.add(component, GridDialogLayoutDataFactory.createHorizontalSpanData(columnCount, GridDialogLayoutData.FILL_HORIZONTAL));
  }

}