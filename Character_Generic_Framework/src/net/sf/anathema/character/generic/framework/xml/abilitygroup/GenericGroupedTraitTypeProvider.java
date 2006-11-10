package net.sf.anathema.character.generic.framework.xml.abilitygroup;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.template.abilities.GroupedTraitType;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.ITraitTypeGroup;
import net.sf.anathema.lib.lang.clone.ICloneable;

public class GenericGroupedTraitTypeProvider implements ICloneable<GenericGroupedTraitTypeProvider> {

  private final List<IGroupedTraitType> groupedTraitTypes = new ArrayList<IGroupedTraitType>();
  private final ITraitTypeGroup traitTypeGroup;

  public GenericGroupedTraitTypeProvider(ITraitTypeGroup traitTypeGroup) {
    this.traitTypeGroup = traitTypeGroup;
  }

  public IGroupedTraitType[] getTraitTypeGroups() {
    return groupedTraitTypes.toArray(new IGroupedTraitType[groupedTraitTypes.size()]);
  }

  public void addGroupedAbilityType(String abilityTypeId, String traitTypeId, String casteId) {
    ITraitType traitType = traitTypeGroup.getById(abilityTypeId);
    groupedTraitTypes.add(new GroupedTraitType(traitType, traitTypeId, casteId));
  }

  @Override
  public GenericGroupedTraitTypeProvider clone() {
    GenericGroupedTraitTypeProvider clone = new GenericGroupedTraitTypeProvider(traitTypeGroup);
    clone.groupedTraitTypes.addAll(groupedTraitTypes);
    return clone;
  }
}