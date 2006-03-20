package net.sf.anathema.character.generic.framework.reporting.datasource;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.framework.reporting.IReportDataSource;
import net.sf.anathema.lib.resources.IResources;

public class BackgroundsDataSource implements IReportDataSource, ITraitDataSource {

  public static final String COLUMN_DESCRIPTION = "DESCRIPTION"; //$NON-NLS-1$

  private final IGenericTrait[] backgrounds;

  public BackgroundsDataSource(IGenericCharacter character, IResources resources) {
    IGenericTrait[] unsortedBackgrounds = character.getBackgrounds();
    int backgroundCount = unsortedBackgrounds.length;
    IGenericTrait[] sortedBackgrounds = new IGenericTrait[backgroundCount];
    if (backgroundCount > 0) {
      I18nedGenericTraitSorter<IGenericTrait> sorter = new I18nedGenericTraitSorter<IGenericTrait>();
      sorter.sortAscending(unsortedBackgrounds, sortedBackgrounds, resources);
    }
    this.backgrounds = sortedBackgrounds;
  }

  public int getRowCount() {
    return backgrounds.length;
  }

  public Object getValue(int currentRow, String columnName) {
    if (COLUMN_PRINT_NAME.equals(columnName)) {
      if (currentRow >= getRowCount()) {
        return ""; //$NON-NLS-1$
      }
      IGenericTrait background = backgrounds[currentRow];
      return background.getType().getId();
    }
    if (COLUMN_VALUE.equals(columnName)) {
      if (currentRow >= getRowCount()) {
        return 0;
      }
      IGenericTrait background = backgrounds[currentRow];
      return new Integer(background.getCurrentValue());
    }
    if (COLUMN_DESCRIPTION.equals(columnName)) {
      return ""; //$NON-NLS-1$
    }
    throw new IllegalArgumentException("No column with name '" + columnName + "'."); //$NON-NLS-1$ //$NON-NLS-2$  
  }
}