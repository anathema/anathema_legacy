package net.sf.anathema.hero.traits.sheet.content;

import net.sf.anathema.hero.traits.model.TraitType;

public interface ValuedTraitReference {

  TraitType getTraitType();

  String getName();

  int getValue();
}