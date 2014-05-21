package net.sf.anathema.character.magic.parser.charms;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.AbilityType;
import net.sf.anathema.hero.traits.model.types.AttributeType;
import net.sf.anathema.hero.traits.model.types.OtherTraitType;
import net.sf.anathema.hero.traits.model.types.VirtueType;

public class TraitTypeFinder {

  public TraitType getTrait(String value) {
    TraitType trait = getAbilityType(value);
    trait = trait == null ? getAttributeType(value) : trait;
    trait = trait == null ? getVirtueType(value) : trait;
    trait = trait == null ? getOtherType(value) : trait;
    return trait;
  }

  private AbilityType getAbilityType(String value) {
    try {
      return AbilityType.valueOf(value);
    } catch (Exception e) {
      return null;
    }
  }

  private AttributeType getAttributeType(String value) {
    try {
      return AttributeType.valueOf(value);
    } catch (Exception e) {
      return null;
    }
  }

  private VirtueType getVirtueType(String value) {
    try {
      return VirtueType.valueOf(value);
    } catch (Exception e) {
      return null;
    }
  }

  private OtherTraitType getOtherType(String value) {
    try {
      return OtherTraitType.valueOf(value);
    } catch (Exception e) {
      return null;
    }
  }
}