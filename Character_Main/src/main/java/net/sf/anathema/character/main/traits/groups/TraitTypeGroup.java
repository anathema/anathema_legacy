package net.sf.anathema.character.main.traits.groups;

import net.sf.anathema.character.main.traits.TraitType;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TraitTypeGroup implements ITraitTypeGroup {

  public static TraitType[] getAllTraitTypes(ITraitTypeGroup... traitTypeGroups) {
    List<TraitType> traitTypes = new ArrayList<>();
    for (ITraitTypeGroup group : traitTypeGroups) {
      Collections.addAll(traitTypes, group.getAllGroupTypes());
    }
    return traitTypes.toArray(new TraitType[traitTypes.size()]);
  }

  private final TraitType[] traitTypes;

  public TraitTypeGroup(TraitType[] traitTypes) {
    this.traitTypes = traitTypes;
  }

  @Override
  public final TraitType getById(String typeId) {
    for (TraitType element : traitTypes) {
      if (element.getId().equals(typeId)) {
        return element;
      }
    }
    throw new IllegalArgumentException("No trait type with found in group with id " + typeId);
  }

  @Override
  public final TraitType[] getAllGroupTypes() {
    return traitTypes;
  }

  @Override
  public final boolean contains(TraitType traitType) {
    return ArrayUtils.contains(traitTypes, traitType);
  }
}