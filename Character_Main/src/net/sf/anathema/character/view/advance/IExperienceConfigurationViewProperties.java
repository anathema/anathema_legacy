package net.sf.anathema.character.view.advance;

import net.sf.anathema.lib.gui.table.columsettings.ITableColumnViewSettings;

import javax.swing.Icon;
import javax.swing.table.TableModel;

public interface IExperienceConfigurationViewProperties {

  TableModel getTableModel();

  ITableColumnViewSettings[] getColumnSettings();

  Icon getDeleteIcon();

  Icon getAddIcon();

  String getTotalString();
}