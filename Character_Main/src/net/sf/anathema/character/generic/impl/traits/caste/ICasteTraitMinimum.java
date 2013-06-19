package net.sf.anathema.character.generic.impl.traits.caste;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.main.hero.Hero;

public interface ICasteTraitMinimum {

  CasteType getCaste();

  int getMinimumValue(Hero hero);
}