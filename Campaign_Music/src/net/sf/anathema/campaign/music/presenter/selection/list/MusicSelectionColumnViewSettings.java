package net.sf.anathema.campaign.music.presenter.selection.list;

import javax.swing.table.TableCellEditor;

import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.lib.gui.table.columsettings.AbstractTableColumnSettings;

public class MusicSelectionColumnViewSettings extends AbstractTableColumnSettings {

  private final IMusicSelectionModel musicSelectionModel;

  public MusicSelectionColumnViewSettings(IMusicSelectionModel musicSelectionModel) {
    super(8);
    this.musicSelectionModel = musicSelectionModel;
  }

  public TableCellEditor getEditor() {
    return new MusicSelectionCellEditor(musicSelectionModel);
  }
}
