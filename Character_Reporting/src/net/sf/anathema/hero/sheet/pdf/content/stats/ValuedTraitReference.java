package net.sf.anathema.hero.sheet.pdf.content.stats;

import net.sf.anathema.character.main.traits.TraitType;

public interface ValuedTraitReference {

  TraitType getTraitType();

  String getName();

  int getValue();
}