package net.sf.anathema.character.lunar.reporting;

import net.sf.anathema.character.lunar.heartsblood.presenter.IAnimalForm;
import net.sf.anathema.character.lunar.heartsblood.presenter.IHeartsBloodModel;
import net.sf.anathema.framework.reporting.IReportDataSource;

public class HeartsBloodDataSource implements IReportDataSource {

  public static final String COLUMN_PRINT_NAME = "Name"; //$NON-NLS-1$
  public static final String COLUMN_STRENGTH = "Strength"; //$NON-NLS-1$
  public static final String COLUMN_STAMINA = "Stamina"; //$NON-NLS-1$
  private final IHeartsBloodModel model;

  public HeartsBloodDataSource(IHeartsBloodModel model) {
    this.model = model;
  }

  public int getRowCount() {
    return model.getEntries().size();
  }

  public Object getValue(int currentRow, String columnName) {
    if (currentRow >= getRowCount()) {
      return ""; //$NON-NLS-1$
    }
    IAnimalForm animalForm = model.getEntries().get(currentRow);
    if (columnName.equals(COLUMN_PRINT_NAME)) {
      return animalForm.getName();
    }
    if (columnName.equals(COLUMN_STRENGTH)) {
      return String.valueOf(animalForm.getStrength());
    }
    if (columnName.equals(COLUMN_STAMINA)) {
      return String.valueOf(animalForm.getStamina());
    }
    return ""; //$NON-NLS-1$
  }
}