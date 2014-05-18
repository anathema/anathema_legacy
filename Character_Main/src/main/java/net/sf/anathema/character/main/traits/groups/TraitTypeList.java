package net.sf.anathema.character.main.traits.groups;

import net.sf.anathema.character.main.traits.TraitType;

import java.util.List;

public interface TraitTypeList {

  TraitType getById(String typeId);

  List<TraitType> getAll();

  int size();

  boolean contains(TraitType traitType);
}