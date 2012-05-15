package net.sf.anathema.lib.gui.table.columsettings;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

// NOT_PUBLISHED
public interface ITableColumnViewSettings {

  TableCellEditor getEditor();

  TableCellRenderer getRenderer();

  boolean isResizable();

  int getPreferredWidth();
}