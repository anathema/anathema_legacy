// Copyright (c) 2004 by disy Informationssysteme GmbH
package net.sf.anathema.lib.gui.table.columsettings;

import javax.swing.table.TableCellEditor;

import net.sf.anathema.lib.gui.table.celleditors.StringCellEditor;

// NOT_PUBLISHED
public class StringTableColumnSettings extends AbstractTableColumnSettings {

  public StringTableColumnSettings() {
    this(8);
  }

  public StringTableColumnSettings(int preferredColumnCount) {
    super(preferredColumnCount);
  }

  public TableCellEditor getEditor() {
    return new StringCellEditor();
  }
}