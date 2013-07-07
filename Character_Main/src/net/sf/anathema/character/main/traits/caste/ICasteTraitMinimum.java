package net.sf.anathema.character.main.traits.caste;

import net.sf.anathema.character.main.caste.CasteType;
import net.sf.anathema.hero.model.Hero;

public interface ICasteTraitMinimum {

  CasteType getCaste();

  int getMinimumValue(Hero hero);
}