package net.sf.anathema.character.library.trait;

import com.google.common.base.Function;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;

import static net.sf.anathema.lib.lang.ArrayUtilities.transform;

public class TraitCollectionUtilities {

  public static Trait[] getVirtues(ITraitCollection collection) {
    return transform(collection.getTraits(VirtueType.values()), Trait.class, new Function<Trait, Trait>() {
      @Override
      public Trait apply(Trait input) {
        return input;
      }
    });
  }

  public static Trait getEssence(ITraitCollection traitConfiguration) {
    return traitConfiguration.getTrait(OtherTraitType.Essence);
  }

  public static Trait getWillpower(ITraitCollection traitConfiguration) {
    return traitConfiguration.getTrait(OtherTraitType.Willpower);
  }
}