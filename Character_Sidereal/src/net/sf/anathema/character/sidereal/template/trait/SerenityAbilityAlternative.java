package net.sf.anathema.character.sidereal.template.trait;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.impl.traits.caste.ICasteTraitMinimum;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.sidereal.caste.SiderealCaste;

public class SerenityAbilityAlternative extends AbstractCasteTraitAlternative {

  public SerenityAbilityAlternative() {
    super(1);
  }

  public ICasteTraitMinimum[] createCraftTraitRange() {
    return createCasteTraitRange(AbilityType.Craft);
  }

  public ICasteTraitMinimum[] createPerformanceTraitRange() {
    return createCasteTraitRange(AbilityType.Performance);
  }

  @Override
  protected int getLowerFreeBound() {
    return 0;
  }

  @Override
  protected int getLowerStrictBound() {
    return 2;
  }

  @Override
  protected ICasteType getCaste() {
    return SiderealCaste.Serenity;
  }
}