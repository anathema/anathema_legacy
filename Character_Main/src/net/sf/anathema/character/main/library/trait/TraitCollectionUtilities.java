package net.sf.anathema.character.main.library.trait;

import com.google.common.base.Function;
import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.character.main.traits.types.VirtueType;
import net.sf.anathema.character.main.model.traits.TraitMap;

import static net.sf.anathema.lib.lang.ArrayUtilities.transform;

public class TraitCollectionUtilities {

  public static Trait[] getVirtues(TraitMap collection) {
    return transform(collection.getTraits(VirtueType.values()), Trait.class, new Function<Trait, Trait>() {
      @Override
      public Trait apply(Trait input) {
        return input;
      }
    });
  }

  public static Trait getEssence(TraitMap traitConfiguration) {
    return traitConfiguration.getTrait(OtherTraitType.Essence);
  }

  public static Trait getWillpower(TraitMap traitConfiguration) {
    return traitConfiguration.getTrait(OtherTraitType.Willpower);
  }
}