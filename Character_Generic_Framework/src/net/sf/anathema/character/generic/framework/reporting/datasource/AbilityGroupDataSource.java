package net.sf.anathema.character.generic.framework.reporting.datasource;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.framework.reporting.IReportDataSource;
import net.sf.anathema.lib.resources.IResources;

public class AbilityGroupDataSource implements IReportDataSource {

  public static final String COLUMN_CROSS = "cross"; //$NON-NLS-1$
  public static final String COLUMN_FAVORED = "favored"; //$NON-NLS-1$
  public static final String COLUMN_NAME = "name"; //$NON-NLS-1$
  public static final String COLUMN_VALUE = "value"; //$NON-NLS-1$

  private final IResources resources;
  private final IGenericTraitCollection collection;
  private final ITraitType[] allGroupTypes;

  public AbilityGroupDataSource(IResources resources, IGenericTraitCollection collection, ITraitType[] allGroupTypes) {
    this.resources = resources;
    this.collection = collection;
    this.allGroupTypes = allGroupTypes;
  }

  public int getRowCount() {
    return allGroupTypes.length;
  }

  public Object getValue(int currentRow, String columnName) {
    if (currentRow >= getRowCount()) {
      return ""; //$NON-NLS-1$
    }
    ITraitType traitType = allGroupTypes[currentRow];
    if (COLUMN_NAME.equals(columnName)) {
      return resources.getString(traitType.getId());
    }
    IFavorableGenericTrait trait = collection.getFavorableTrait(traitType);
    if (COLUMN_VALUE.equals(columnName)) {
      return new Integer(trait.getCurrentValue());
    }
    if (COLUMN_FAVORED.equals(columnName)) {
      return new Boolean(trait.isCasteOrFavored());
    }
    if (COLUMN_CROSS.equals(columnName)) {
      return new Boolean(traitType.equals(AbilityType.Larceny)
          || traitType.equals(AbilityType.Athletics)
          || traitType.equals(AbilityType.Stealth)
          || traitType.equals(AbilityType.Dodge)
          || traitType.equals(AbilityType.Ride));
    }
    throw new IllegalArgumentException("Illegal Colum Name:" + columnName); //$NON-NLS-1$
  }
}