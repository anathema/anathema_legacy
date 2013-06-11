package net.sf.anathema.character.library.trait.view;

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
    container.setLayout(new MigLayout(LayoutUtils.withoutInsets().wrapAfter(columns.length).gridGapX("15")));
    for (JPanel column : columns) {
      container.add(column, new CC().alignY("top"));
    }
  }
}