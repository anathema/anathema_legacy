package net.sf.anathema.character.view.advance;

import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.table.columsettings.ITableColumnViewSettings;

import javax.swing.table.TableModel;

public interface IExperienceConfigurationViewProperties {

  TableModel getTableModel();

  ITableColumnViewSettings[] getColumnSettings();

  RelativePath getDeleteIcon();

  RelativePath getAddIcon();

  String getTotalString();
}