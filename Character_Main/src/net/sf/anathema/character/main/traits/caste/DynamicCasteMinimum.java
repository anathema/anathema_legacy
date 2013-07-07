package net.sf.anathema.character.main.traits.caste;

import net.sf.anathema.character.main.caste.CasteType;
import net.sf.anathema.character.main.traits.ITraitMinimum;
import net.sf.anathema.hero.model.Hero;

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