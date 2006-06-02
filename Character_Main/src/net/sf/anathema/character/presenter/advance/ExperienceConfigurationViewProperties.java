package net.sf.anathema.character.presenter.advance;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.table.TableModel;

import net.sf.anathema.character.view.advance.IExperienceConfigurationViewProperties;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.gui.table.columsettings.ITableColumnViewSettings;
import net.sf.anathema.lib.gui.table.columsettings.IntegerTableColumnSettings;
import net.sf.anathema.lib.gui.table.columsettings.StringTableColumnSettings;
import net.sf.anathema.lib.resources.IResources;

public class ExperienceConfigurationViewProperties implements IExperienceConfigurationViewProperties {

  private final TableModel tableModel;
  private final BasicUi basicUi;
  private IResources resources;

  public ExperienceConfigurationViewProperties(IResources resources, TableModel tableModel) {
    this.basicUi = new BasicUi(resources);
    this.tableModel = tableModel;
    this.resources = resources;
  }

  public TableModel getTableModel() {
    return tableModel;
  }

  public ITableColumnViewSettings[] getColumnSettings() {
    return new ITableColumnViewSettings[] {
        new StringTableColumnSettings(),
        new IntegerTableColumnSettings(-10000, 10000, 5, Color.RED) };
  }

  public Icon getDeleteIcon() {
    return basicUi.getMediumRemoveIcon();
  }

  public Icon getAddIcon() {
    return basicUi.getMediumAddIcon();
  }

  public String getTotalString() {
    return resources.getString("CardView.Experience.Total"); //$NON-NLS-1$
  }
}