package net.sf.anathema.hero.traits.sheet.content;

import net.sf.anathema.character.main.traits.TraitType;

public interface ValuedTraitReference {

  TraitType getTraitType();

  String getName();

  int getValue();
}