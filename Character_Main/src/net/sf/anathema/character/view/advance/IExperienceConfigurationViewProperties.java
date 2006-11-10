package net.sf.anathema.character.view.advance;

import javax.swing.Icon;
import javax.swing.table.TableModel;

import net.sf.anathema.lib.gui.table.columsettings.ITableColumnViewSettings;

public interface IExperienceConfigurationViewProperties {

  public TableModel getTableModel();

  public ITableColumnViewSettings[] getColumnSettings();

  public Icon getDeleteIcon();

  public Icon getAddIcon();

  public String getTotalString();
}