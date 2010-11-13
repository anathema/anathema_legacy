package net.sf.anathema.lib.gui.table.columsettings;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

// NOT_PUBLISHED
public interface ITableColumnViewSettings {

  public TableCellEditor getEditor();

  public TableCellRenderer getRenderer();

  public boolean isResizable();

  public int getPreferredWidth();
}