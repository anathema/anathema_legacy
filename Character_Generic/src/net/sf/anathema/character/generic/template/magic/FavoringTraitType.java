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
    public AbilityType[] getTraitTypes() {
      return net.sf.anathema.character.generic.traits.types.AbilityType.values();
    }

    @Override
    public boolean canFavorType(ITraitType type) {
      return type instanceof AbilityType;
    }

    @Override
    public ITraitType getSpellFavoringType() {
      return net.sf.anathema.character.generic.traits.types.AbilityType.Occult;
    }
  },
  AttributeType {
    @Override
    public AttributeType[] getTraitTypes() {
      return net.sf.anathema.character.generic.traits.types.AttributeType.values();
    }

    @Override
    public boolean canFavorType(ITraitType type) {
      return type instanceof AttributeType;
    }

    @Override
    public ITraitType getSpellFavoringType() {
      return net.sf.anathema.character.generic.traits.types.AttributeType.Intelligence;
    }
  },
  VirtueType {
    @Override
    public VirtueType[] getTraitTypes() {
      return net.sf.anathema.character.generic.traits.types.VirtueType.values();
    }

    @Override
    public boolean canFavorType(ITraitType type) {
      return false;
    }

    @Override
    public ITraitType getSpellFavoringType() {
      throw new UnsupportedOperationException("Ghosts cannot learn Spells.");
    }
  },
  YoziType {
    @Override
    public YoziType[] getTraitTypes() {
      return net.sf.anathema.character.generic.traits.types.YoziType.values();
    }

    @Override
    public boolean canFavorType(ITraitType type) {
      return type instanceof YoziType;
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

  public abstract ITraitType[] getTraitTypes();

  public abstract boolean canFavorType(ITraitType type);

  public abstract ITraitType getSpellFavoringType();
}