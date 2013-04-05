package net.sf.anathema.lib.gui.table.columsettings;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public interface ITableColumnViewSettings {

  TableCellEditor getEditor();

  TableCellRenderer getRenderer();

  int getPreferredWidth();
}