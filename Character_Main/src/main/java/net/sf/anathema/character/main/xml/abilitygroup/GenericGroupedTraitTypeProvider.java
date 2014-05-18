package net.sf.anathema.character.main.xml.abilitygroup;

import net.sf.anathema.character.main.template.abilities.GroupedTraitType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.lists.TraitTypeList;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;
import net.sf.anathema.lib.lang.clone.ICloneable;

import java.util.ArrayList;
import java.util.List;

public class GenericGroupedTraitTypeProvider implements ICloneable<GenericGroupedTraitTypeProvider> {

  // This is volatile instead of final to allow clone to be implemented
  private volatile List<GroupedTraitType> groupedTraitTypes = new ArrayList<>();
  private final TraitTypeList traitTypeGroup;

  public GenericGroupedTraitTypeProvider(TraitTypeList traitTypeGroup) {
    this.traitTypeGroup = traitTypeGroup;
  }

  public GroupedTraitType[] getTraitTypeGroups() {
    return groupedTraitTypes.toArray(new GroupedTraitType[groupedTraitTypes.size()]);
  }

  public void addGroupedAbilityType(String traitId, String traitGroupId, List<String> traitCasteIds) {
    TraitType traitType = traitTypeGroup.getById(traitId);
    GroupedTraitType type = new GroupedTraitType(traitType, traitGroupId, traitCasteIds);
    groupedTraitTypes.add(type);
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