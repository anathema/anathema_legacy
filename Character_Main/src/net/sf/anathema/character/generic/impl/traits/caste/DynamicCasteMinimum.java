package net.sf.anathema.character.generic.impl.traits.caste;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.generic.traits.ITraitMinimum;
import net.sf.anathema.character.main.hero.Hero;

public class DynamicCasteMinimum implements ICasteTraitMinimum {

  private final ITraitMinimum minimum;
  private final CasteType caste;

  public DynamicCasteMinimum(CasteType caste, ITraitMinimum range) {
    this.caste = caste;
    this.minimum = range;
  }

  @Override
  public CasteType getCaste() {
    return caste;
  }

  @Override
  public int getMinimumValue(Hero hero) {
    return minimum.getMinimumValue(hero);
  }
}