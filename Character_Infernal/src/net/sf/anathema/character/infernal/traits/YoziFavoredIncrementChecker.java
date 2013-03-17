package net.sf.anathema.character.infernal.traits;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.YoziType;
import net.sf.anathema.character.impl.model.traits.creation.FavoredIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class YoziFavoredIncrementChecker {

  public static IIncrementChecker create(ICoreTraitConfiguration traitConfiguration) {
    int maxFavoredAttributeCount = 1;
    ITraitType[] traitTypes =  YoziType.values();
    return new FavoredIncrementChecker(maxFavoredAttributeCount, traitTypes, traitConfiguration);
  }
}