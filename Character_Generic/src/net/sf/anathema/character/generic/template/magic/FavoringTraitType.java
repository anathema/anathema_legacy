package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.generic.traits.types.YoziType;
import net.sf.anathema.lib.util.Identified;

public enum FavoringTraitType implements Identified {
  AbilityType {
    @Override
    public AbilityType[] getTraitTypesForGenericCharms() {
      return net.sf.anathema.character.generic.traits.types.AbilityType.values();
    }

    @Override
    public ITraitType getSpellFavoringType() {
      return net.sf.anathema.character.generic.traits.types.AbilityType.Occult;
    }
  },
  AttributeType {
    @Override
    public AttributeType[] getTraitTypesForGenericCharms() {
      return net.sf.anathema.character.generic.traits.types.AttributeType.values();
    }

    @Override
    public ITraitType getSpellFavoringType() {
      return net.sf.anathema.character.generic.traits.types.AttributeType.Intelligence;
    }
  },
  VirtueType {
    @Override
    public VirtueType[] getTraitTypesForGenericCharms() {
      return net.sf.anathema.character.generic.traits.types.VirtueType.values();
    }

    @Override
    public ITraitType getSpellFavoringType() {
      throw new UnsupportedOperationException("Ghosts cannot learn Spells.");
    }
  },
  YoziType {
    @Override
    public YoziType[] getTraitTypesForGenericCharms() {
      return net.sf.anathema.character.generic.traits.types.YoziType.values();
    }

    @Override
    public ITraitType getSpellFavoringType() {
      return net.sf.anathema.character.generic.traits.types.AbilityType.Occult;
    }
  };

  @Override
  public String getId() {
    return name();
  }

  public abstract ITraitType[] getTraitTypesForGenericCharms();

  public abstract ITraitType getSpellFavoringType();
}