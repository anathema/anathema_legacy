package net.sf.anathema.character.main.traits.caste;

import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.model.Hero;

public interface ICasteTraitMinimum {

  CasteType getCaste();

  int getMinimumValue(Hero hero);
}