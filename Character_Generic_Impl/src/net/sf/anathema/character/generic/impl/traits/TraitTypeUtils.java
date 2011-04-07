package net.sf.anathema.character.generic.impl.traits;

import java.util.ArrayList;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.generic.traits.types.YoziType;

public class TraitTypeUtils {

  private final ArrayList<ITraitType> allPrerequisiteTypeList = new ArrayList<ITraitType>();

  private ITraitType[] getAllCoreTraitTypes() {
    if (allPrerequisiteTypeList.isEmpty()) {
      for (ITraitType type : AbilityType.values()) {
        allPrerequisiteTypeList.add(type);
      }
      for (ITraitType type : AttributeType.values()) {
        allPrerequisiteTypeList.add(type);
      }
      for (ITraitType type : VirtueType.values()) {
        allPrerequisiteTypeList.add(type);
      }
      for (ITraitType type : YoziType.values())
      	allPrerequisiteTypeList.add(type);
      for (ITraitType type : OtherTraitType.values()) {
        allPrerequisiteTypeList.add(type);
      }
    }
    return allPrerequisiteTypeList.toArray(new ITraitType[allPrerequisiteTypeList.size()]);
  }

  public ITraitType getTraitTypeById(String id) {
    ITraitType[] allTraits = getAllCoreTraitTypes();
    for (ITraitType type : allTraits) {
      if (id.equals(type.getId())) {
        return type;
      }
    }
    throw new IllegalArgumentException("No trait type with id: " + id); //$NON-NLS-1$
  }
}