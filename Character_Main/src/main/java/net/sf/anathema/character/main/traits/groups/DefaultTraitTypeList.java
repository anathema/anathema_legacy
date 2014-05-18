package net.sf.anathema.character.main.traits.groups;

import net.sf.anathema.character.main.traits.TraitType;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultTraitTypeList implements TraitTypeList {

  public static TraitType[] getAllTraitTypes(TraitTypeList... traitTypeGroups) {
    List<TraitType> traitTypes = new ArrayList<>();
    for (TraitTypeList group : traitTypeGroups) {
      traitTypes.addAll(group.getAll());
    }
    return traitTypes.toArray(new TraitType[traitTypes.size()]);
  }

  private final TraitType[] traitTypes;

  public DefaultTraitTypeList(TraitType[] traitTypes) {
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
  public List<TraitType> getAll() {
    return Arrays.asList(traitTypes);
  }

  @Override
  public int size() {
    return traitTypes.length;
  }

  @Override
  public final boolean contains(TraitType traitType) {
    return ArrayUtils.contains(traitTypes, traitType);
  }
}