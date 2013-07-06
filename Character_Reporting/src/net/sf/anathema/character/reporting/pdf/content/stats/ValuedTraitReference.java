package net.sf.anathema.character.reporting.pdf.content.stats;

import net.sf.anathema.character.generic.traits.TraitType;

public interface ValuedTraitReference {

  TraitType getTraitType();

  String getName();

  int getValue();
}