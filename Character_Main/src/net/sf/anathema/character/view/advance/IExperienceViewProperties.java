package net.sf.anathema.character.view.advance;

import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.table.columsettings.ITableColumnViewSettings;

public interface IExperienceViewProperties {

  //TODO: (Swing->FX) Swing throughout
  ITableColumnViewSettings[] getColumnSettings();

  RelativePath getDeleteIcon();

  RelativePath getAddIcon();

  String getTotalString();

  String getPointHeader();

  String getDescriptionHeader();
}