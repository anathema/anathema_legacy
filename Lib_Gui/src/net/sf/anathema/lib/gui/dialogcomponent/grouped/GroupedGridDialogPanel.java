package net.sf.anathema.lib.gui.dialogcomponent.grouped;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.gui.layout.SingleOverallComponent;

public class GroupedGridDialogPanel {

  private final IGridDialogPanel[] columns;
  private int columnIndex = -1;

  public GroupedGridDialogPanel(int columnCount) {
    columns = new DefaultGridDialogPanel[columnCount];
    for (int i = 0; i < columns.length; i++) {
      columns[i] = new DefaultGridDialogPanel();
    }
  }

  public void startNewGroup(String groupLabel) {
    increaseColumnIndex();
    if (groupLabel != null) {
      getCurrentColumn().add(new SingleOverallComponent(new JLabel(groupLabel)));
    }
  }

  private void increaseColumnIndex() {
    columnIndex++;
    if (columnIndex >= columns.length) {
      columnIndex = 0;
    }
  }

  private IGridDialogPanel getCurrentColumn() {
    return columns[columnIndex];
  }

  public void addEntry(IGridDialogPanelContent entry) {
    entry.addComponents(getCurrentColumn());
  }

  public void addOverallView(JPanel container) {
    GridDialogLayout layout = new GridDialogLayout(columns.length, false);
    layout.setHorizontalSpacing(15);
    container.setLayout(layout);
    for (IGridDialogPanel column : columns) {
      GridDialogLayoutData data = new GridDialogLayoutData();
      data.setVerticalAlignment(GridAlignment.BEGINNING);
      container.add(column.getComponent(), data);
    }
  }
}