package net.sf.anathema.character.library.trait;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;

public class TraitCollectionUtitlies {

  private TraitCollectionUtitlies() {
    // nothing to do
  }

  public static IDefaultTrait[] getVirtues(ITraitCollection collection) {
    return ArrayUtilities.transform(
        collection.getTraits(VirtueType.values()),
        IDefaultTrait.class,
        new ITransformer<ITrait, IDefaultTrait>() {
          public IDefaultTrait transform(ITrait input) {
            return (IDefaultTrait) input;
          }
        });
  }

  public static IDefaultTrait getEssence(ITraitCollection traitConfiguration) {
    return (IDefaultTrait) traitConfiguration.getTrait(OtherTraitType.Essence);
  }

  public static IDefaultTrait getWillpower(ITraitCollection traitConfiguration) {
    return (IDefaultTrait) traitConfiguration.getTrait(OtherTraitType.Willpower);
  }
}