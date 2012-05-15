package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.lib.collection.ArrayUtilities;
import net.sf.anathema.lib.util.ITransformer;

public class TraitCollectionUtilities {

  private TraitCollectionUtilities() {
    // nothing to do
  }

  public static IDefaultTrait[] getVirtues(ITraitCollection collection) {
    return ArrayUtilities.transform(
        collection.getTraits(VirtueType.values()),
        IDefaultTrait.class,
        new ITransformer<ITrait, IDefaultTrait>() {
          @Override
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