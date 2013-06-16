package net.sf.anathema.character.presenter.advance;

import net.sf.anathema.character.view.advance.IExperienceViewProperties;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.table.columsettings.ITableColumnViewSettings;
import net.sf.anathema.lib.gui.table.columsettings.IntegerTableColumnSettings;
import net.sf.anathema.lib.gui.table.columsettings.StringTableColumnSettings;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.table.TableModel;
import java.awt.Color;

public class ExperienceViewProperties implements IExperienceViewProperties {

  private final TableModel tableModel;
  private final BasicUi basicUi;
  private Resources resources;

  public ExperienceViewProperties(Resources resources, TableModel tableModel) {
    this.basicUi = new BasicUi();
    this.tableModel = tableModel;
    this.resources = resources;
  }

  @Override
  public TableModel getTableModel() {
    return tableModel;
  }

  @Override
  public ITableColumnViewSettings[] getColumnSettings() {
    return new ITableColumnViewSettings[]{new StringTableColumnSettings(), new IntegerTableColumnSettings(-10000, 10000, 5, Color.RED)};
  }

  @Override
  public RelativePath getDeleteIcon() {
    return basicUi.getRemoveIconPath();
  }

  @Override
  public RelativePath getAddIcon() {
    return basicUi.getAddIconPath();
  }

  @Override
  public String getTotalString() {
    return resources.getString("CardView.Experience.Total");
  }
}