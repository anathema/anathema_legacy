package net.sf.anathema.character.library.trait.view;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.lib.gui.layout.LayoutUtils;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GroupedColumnPanel {
  private final JPanel[] columns;
  private int columnIndex = -1;

  public GroupedColumnPanel(JComponent parent, int columnCount) {
    columns = new JPanel[columnCount];
    for (int i = 0; i < columns.length; i++) {
      columns[i] = new JPanel(new MigLayout(LayoutUtils.withoutInsets().wrapAfter(2)));
    }
    addOverallView(parent);
  }

  public void startNewGroup(String groupLabel) {
    increaseColumnIndex();
    if (groupLabel != null) {
      getCurrentColumn().add(new JLabel(groupLabel), new CC().spanX().growX().pushX());
    }
  }

  private void increaseColumnIndex() {
    columnIndex++;
    if (columnIndex >= columns.length) {
      columnIndex = 0;
    }
  }

  public JPanel getCurrentColumn() {
    return columns[columnIndex];
  }

  private void addOverallView(JComponent container) {
    GridDialogLayout layout = new GridDialogLayout(columns.length, false);
    layout.setHorizontalSpacing(15);
    container.setLayout(layout);
    GridDialogLayoutData data = GridDialogLayoutDataFactory.createTopData();
    for (JPanel column : columns) {
      container.add(column, data);
    }
  }
}