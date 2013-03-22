package net.sf.anathema.character.generic.impl.traits;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.generic.traits.types.YoziType;

import java.util.ArrayList;

import static java.util.Collections.addAll;

public class TraitTypeUtils {

  private final ArrayList<ITraitType> allPrerequisiteTypeList = new ArrayList<>();

  public ITraitType getTraitTypeById(String id) {
    for (ITraitType type : getAllCoreTraitTypes()) {
      if (id.equals(type.getId())) {
        return type;
      }
    }
    throw new IllegalArgumentException("No trait type with id: " + id);
  }

  private Iterable<ITraitType> getAllCoreTraitTypes() {
    if (allPrerequisiteTypeList.isEmpty()) {
      addAll(allPrerequisiteTypeList, AbilityType.values());
      addAll(allPrerequisiteTypeList, AttributeType.values());
      addAll(allPrerequisiteTypeList, VirtueType.values());
      addAll(allPrerequisiteTypeList, YoziType.values());
      addAll(allPrerequisiteTypeList, OtherTraitType.values());
    }
    return allPrerequisiteTypeList;
  }
}