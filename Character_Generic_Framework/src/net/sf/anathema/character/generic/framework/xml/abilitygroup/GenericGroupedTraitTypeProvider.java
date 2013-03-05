package net.sf.anathema.character.generic.framework.xml.abilitygroup;

import net.sf.anathema.character.generic.template.abilities.GroupedTraitType;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.ITraitTypeGroup;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;
import net.sf.anathema.lib.lang.clone.ICloneable;

import java.util.ArrayList;
import java.util.List;

public class GenericGroupedTraitTypeProvider implements ICloneable<GenericGroupedTraitTypeProvider> {

  // This is volatile instead of final to allow clone to be implemented
  private volatile List<IGroupedTraitType> groupedTraitTypes = new ArrayList<>();
  private final ITraitTypeGroup traitTypeGroup;

  public GenericGroupedTraitTypeProvider(ITraitTypeGroup traitTypeGroup) {
    this.traitTypeGroup = traitTypeGroup;
  }

  public IGroupedTraitType[] getTraitTypeGroups() {
    return groupedTraitTypes.toArray(new IGroupedTraitType[groupedTraitTypes.size()]);
  }

  public void addGroupedAbilityType(String abilityTypeId, String traitTypeId, String groupCasteId, List<String> traitCasteIds) {
    ITraitType traitType = traitTypeGroup.getById(abilityTypeId);
    GroupedTraitType type = createType(traitTypeId, groupCasteId, traitCasteIds, traitType);
    groupedTraitTypes.add(type);
  }

  private GroupedTraitType createType(String traitTypeId, String groupCasteId, List<String> traitCasteIds, ITraitType traitType) {
    if (traitCasteIds.isEmpty()) {
      return new GroupedTraitType(traitType, traitTypeId, groupCasteId);
    }
    return new GroupedTraitType(traitType, traitCasteIds, traitTypeId);
  }

  @Override
  public GenericGroupedTraitTypeProvider clone() {
    GenericGroupedTraitTypeProvider clone;
    try {
      clone = (GenericGroupedTraitTypeProvider) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException(e);
    }
    clone.groupedTraitTypes = new ArrayList<>(groupedTraitTypes);
    return clone;
  }
}