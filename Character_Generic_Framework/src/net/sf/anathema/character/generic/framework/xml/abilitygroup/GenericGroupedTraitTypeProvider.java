package net.sf.anathema.character.generic.framework.xml.abilitygroup;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.character.generic.template.abilities.GroupedTraitType;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.ITraitTypeGroup;
import net.sf.anathema.lib.lang.clone.ICloneable;

public class GenericGroupedTraitTypeProvider implements ICloneable<GenericGroupedTraitTypeProvider> {

  // This is volatile instead of final to allow clone to be implemented
  private volatile List<IGroupedTraitType> groupedTraitTypes = new ArrayList<IGroupedTraitType>();
  private final ITraitTypeGroup traitTypeGroup;

  public GenericGroupedTraitTypeProvider(ITraitTypeGroup traitTypeGroup) {
    this.traitTypeGroup = traitTypeGroup;
  }

  public IGroupedTraitType[] getTraitTypeGroups() {
    return groupedTraitTypes.toArray(new IGroupedTraitType[groupedTraitTypes.size()]);
  }

  public void addGroupedAbilityType(String abilityTypeId, String traitTypeId, String groupCasteId, List<String> traitCasteIds) {
    ITraitType traitType = traitTypeGroup.getById(abilityTypeId);
    groupedTraitTypes.add(traitCasteIds.size() > 0 ?
    		new GroupedTraitType(traitType, traitCasteIds, traitTypeId) :
    		new GroupedTraitType(traitType, traitTypeId, groupCasteId));
  }

  @Override
  public GenericGroupedTraitTypeProvider clone() {
    GenericGroupedTraitTypeProvider clone;
    try {
      clone = (GenericGroupedTraitTypeProvider)super.clone();
    } catch (CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException(e);
    }
    
    clone.groupedTraitTypes = new ArrayList<IGroupedTraitType>(groupedTraitTypes);
    return clone;
  }
}