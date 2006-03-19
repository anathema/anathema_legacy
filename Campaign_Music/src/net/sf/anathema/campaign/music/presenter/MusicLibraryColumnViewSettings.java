package net.sf.anathema.campaign.music.presenter;

import javax.swing.table.TableCellEditor;

import net.sf.anathema.campaign.music.model.libary.ILibraryControl;
import net.sf.anathema.lib.gui.table.columsettings.AbstractTableColumnSettings;

public class MusicLibraryColumnViewSettings extends AbstractTableColumnSettings {

  private final ILibraryControl control;

  public MusicLibraryColumnViewSettings(ILibraryControl control) {
    super(8);
    this.control = control;
  }

  public TableCellEditor getEditor() {
    return new MusicLibraryCellEditor(control);
  }
}
