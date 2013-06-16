package net.sf.anathema.character.library.trait;

import com.google.common.base.Function;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;

import static net.sf.anathema.lib.lang.ArrayUtilities.transform;

public class TraitCollectionUtilities {

  public static IDefaultTrait[] getVirtues(ITraitCollection collection) {
    return transform(collection.getTraits(VirtueType.values()), IDefaultTrait.class, new Function<ITrait, IDefaultTrait>() {
      @Override
      public IDefaultTrait apply(ITrait input) {
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