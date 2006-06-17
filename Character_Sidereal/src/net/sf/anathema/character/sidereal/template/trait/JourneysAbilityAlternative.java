package net.sf.anathema.character.sidereal.template.trait;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.impl.traits.caste.ICasteTraitMinimum;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.sidereal.caste.ISiderealCasteVisitor;
import net.sf.anathema.character.sidereal.caste.SiderealCaste;

public class JourneysAbilityAlternative extends AbstractCasteTraitAlternative {

  public JourneysAbilityAlternative() {
    super(1);
  }

  public ICasteTraitMinimum[] createRideTraitRange() {
    return createCasteTraitRange(AbilityType.Ride);
  }

  public ICasteTraitMinimum[] createSailTraitRange() {
    return createCasteTraitRange(AbilityType.Sail);
  }

  @Override
  protected int getLowerFreeBound() {
    return 0;
  }

  @Override
  protected int getLowerStrictBound() {
    return 3;
  }

  @Override
  protected ICasteType<ISiderealCasteVisitor> getCaste() {
    return SiderealCaste.Journeys;
  }
}