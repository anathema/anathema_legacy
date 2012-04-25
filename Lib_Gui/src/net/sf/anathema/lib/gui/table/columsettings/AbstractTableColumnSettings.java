package net.sf.anathema.lib.gui.table.columsettings;

import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

public abstract class AbstractTableColumnSettings implements ITableColumnViewSettings {

  private final int preferredWidth;

  public AbstractTableColumnSettings(int preferredColumnCount) {
    this.preferredWidth = (int) new JTextField(preferredColumnCount).getPreferredSize().getWidth();
  }

  @Override
  public final int getPreferredWidth() {
    return preferredWidth;
  }

  @Override
  public TableCellRenderer getRenderer() {
    return null;
  }

  @Override
  public boolean isResizable() {
    return true;
  }
}