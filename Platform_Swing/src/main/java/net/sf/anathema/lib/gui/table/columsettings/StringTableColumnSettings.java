package net.sf.anathema.lib.gui.table.columsettings;

import net.sf.anathema.lib.gui.table.celleditors.StringCellEditor;

import javax.swing.table.TableCellEditor;

public class StringTableColumnSettings extends AbstractTableColumnSettings {

  public StringTableColumnSettings() {
    this(8);
  }

  public StringTableColumnSettings(int preferredColumnCount) {
    super(preferredColumnCount);
  }

  @Override
  public TableCellEditor getEditor() {
    return new StringCellEditor();
  }
}