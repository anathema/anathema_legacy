package net.sf.anathema.character.generic.framework.reporting.datasource;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.IdentifiedTraitTypeGroup;
import net.sf.anathema.framework.reporting.IReportDataSource;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

public class AbilitySetDataSource implements IReportDataSource {

  public static final String COLUMN_NAME = "name"; //$NON-NLS-1$
  public static final String COLUMN_GROUP_DATASOURCE = "groupDataSource"; //$NON-NLS-1$

  private final IResources resources;
  private final IGenericCharacter character;
  private final IIdentifiedTraitTypeGroup[] abilityTypeGroups;

  public AbilitySetDataSource(IResources resources, IGenericCharacter character) {
    this.resources = resources;
    this.character = character;
    List<String> groupIds = new ArrayList<String>();
    MultiEntryMap<String, ITraitType> groupMap = new MultiEntryMap<String, ITraitType>();
    ICharacterTemplate template = character.getTemplate();
    IGroupedTraitType[] abilityGroups = template.getAbilityGroups();
    for (IGroupedTraitType type : abilityGroups) {
      String groupId = type.getGroupId();
      groupMap.add(groupId, type.getTraitType());
      if (!groupIds.contains(groupId)) {
        groupIds.add(groupId);
      }
    }
    this.abilityTypeGroups = new IIdentifiedTraitTypeGroup[groupIds.size()];
    for (int index = 0; index < groupIds.size(); index++) {
      abilityTypeGroups[index] = new IdentifiedTraitTypeGroup(groupMap.get(groupIds.get(index)).toArray(
          new ITraitType[0]), new Identificate(groupIds.get(index)));
    }
  }

  public int getRowCount() {
    return abilityTypeGroups.length;
  }

  public Object getValue(int currentRow, String columnName) {
    if (currentRow >= getRowCount()) {
      return ""; //$NON-NLS-1$
    }
    if (COLUMN_NAME.equals(columnName)) {
      return resources.getString("AbilityGroup." //$NON-NLS-1$
          + abilityTypeGroups[currentRow].getGroupId());
    }
    if (COLUMN_GROUP_DATASOURCE.equals(columnName)) {
      return new AbilityGroupDataSource(resources, character.getTraitCollection(), abilityTypeGroups[currentRow].getAllGroupTypes());
    }
    throw new IllegalArgumentException("Illegal Colum Name:" + columnName); //$NON-NLS-1$
  }
}