package net.sf.anathema.character.generic.framework.reporting.datasource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.framework.reporting.IReportDataSource;

public class SpecialtiesDataSource implements IReportDataSource {

  public static final String COLUMN_PRINT_NAME = "PRINT_NAME"; //$NON-NLS-1$
  public static final String COLUMN_VALUE = "VALUE"; //$NON-NLS-1$
  private final Map<INamedGenericTrait, ITraitType> abilityTypeBySpecialty = new HashMap<INamedGenericTrait, ITraitType>();
  private final List<INamedGenericTrait> specialtyList = new ArrayList<INamedGenericTrait>();

  public SpecialtiesDataSource(IGenericCharacter character) {
    for (IGroupedTraitType abilityGroup : character.getTemplate().getAbilityGroups()) {
      ITraitType type = abilityGroup.getTraitType();
      for (INamedGenericTrait specialty : character.getSpecialties(type)) {
        if (specialty.getCurrentValue() != 0) {
          abilityTypeBySpecialty.put(specialty, type);
          specialtyList.add(specialty);
        }
      }
    }
  }

  public Object getValue(int currentRow, String columnName) {
    if (COLUMN_PRINT_NAME.equals(columnName)) {
      if (currentRow >= getRowCount()) {
        return ""; //$NON-NLS-1$
      }
      INamedGenericTrait specialty = specialtyList.get(currentRow);
      // todo i18n
      return abilityTypeBySpecialty.get(specialty).getId() + " - " + specialty.getName(); //$NON-NLS-1$
    }
    if (COLUMN_VALUE.equals(columnName)) {
      if (currentRow >= getRowCount()) {
        return new Integer(0);
      }
      INamedGenericTrait specialty = specialtyList.get(currentRow);
      return new Integer(specialty.getCurrentValue());
    }
    throw new IllegalArgumentException("No column with name '" + columnName + "'."); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public int getRowCount() {
    return specialtyList.size();
  }
}