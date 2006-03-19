package net.sf.anathema.character.lunar.reporting;

import net.sf.anathema.character.lunar.beastform.model.gift.IGiftModel;
import net.sf.anathema.framework.reporting.IReportDataSource;
import net.sf.anathema.lib.resources.IResources;

public class BeastformGiftDatasource implements IReportDataSource {

  public static final String COLUMN_NAME = "Name"; //$NON-NLS-1$
  private final IGiftModel model;
  private final IResources resources;

  public BeastformGiftDatasource(IGiftModel model, IResources resources) {
    this.model = model;
    this.resources = resources;
  }

  public int getRowCount() {
    return model.getSelectedQualities().length;
  }

  public Object getValue(int currentRow, String columnName) {
    if (currentRow >= getRowCount()) {
      return ""; //$NON-NLS-1$
    }
    if (COLUMN_NAME.equals(columnName)) {
      return resources.getString("DeadlyBeastmanTransformation.Gift." //$NON-NLS-1$
          + model.getSelectedQualities()[currentRow].getQuality().getId());
    }
    throw new IllegalArgumentException("Illegal Colum Name:" + columnName); //$NON-NLS-1$
  }
}