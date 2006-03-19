package net.sf.anathema.character.lunar.reporting;

import net.sf.anathema.character.lunar.renown.presenter.IRenownModel;
import net.sf.anathema.framework.reporting.IReportDataSource;
import net.sf.anathema.lib.resources.IResources;

public class RenownDataSource implements IReportDataSource {
  public static final String COLUMN_PRINT_NAME = "PrintName"; //$NON-NLS-1$
  public static final String COLUMN_VALUE = "Value"; //$NON-NLS-1$

  private final IRenownModel renownModel;
  private final IResources resources;

  public RenownDataSource(IResources resources, IRenownModel renownModel) {
    this.resources = resources;
    this.renownModel = renownModel;
  }

  public int getRowCount() {
    return renownModel.getAllTraits().length + 1;
  }

  public Object getValue(int currentRow, String columnName) {
    if (currentRow >= getRowCount()) {
      return ""; //$NON-NLS-1$
    }
    if (columnName.equals(COLUMN_PRINT_NAME)) {
      return resources.getString("Lunar.Renown." + renownModel.getAllTraits()[currentRow].getType().getId()); //$NON-NLS-1$
    }
    if (columnName.equals(COLUMN_VALUE)) {
      return String.valueOf(renownModel.getAllTraits()[currentRow].getCurrentValue());
    }
    throw new IllegalArgumentException("Illegal Column Name: " + columnName); //$NON-NLS-1$
  }
}