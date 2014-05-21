package net.sf.anathema.hero.traits.model.lists;

import net.sf.anathema.hero.traits.model.TraitType;

import java.util.List;

public interface TraitTypeList {

  TraitType getById(String typeId);

  List<TraitType> getAll();

  int size();

  boolean contains(TraitType traitType);
}