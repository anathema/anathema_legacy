package net.sf.anathema.campaign.music.presenter;

import net.sf.anathema.campaign.music.model.libary.ILibraryControl;
import net.sf.anathema.lib.gui.table.columsettings.AbstractTableColumnSettings;

import javax.swing.table.TableCellEditor;

public class MusicLibraryColumnViewSettings extends AbstractTableColumnSettings {

  private final ILibraryControl control;

  public MusicLibraryColumnViewSettings(ILibraryControl control) {
    super(8);
    this.control = control;
  }

  @Override
  public TableCellEditor getEditor() {
    return new MusicLibraryCellEditor(control);
  }
}
