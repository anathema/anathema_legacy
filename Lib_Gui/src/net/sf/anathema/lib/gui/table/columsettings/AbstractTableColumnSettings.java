package net.sf.anathema.lib.gui.table.columsettings;

import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

public abstract class AbstractTableColumnSettings implements ITableColumnViewSettings {

  private final int preferredWidth;

  public AbstractTableColumnSettings(int preferredColumnCount) {
    this.preferredWidth = (int) new JTextField(preferredColumnCount).getPreferredSize().getWidth();
  }

  public final int getPreferredWidth() {
    return preferredWidth;
  }

  public TableCellRenderer getRenderer() {
    return null;
  }

  public boolean isResizable() {
    return true;
  }
}