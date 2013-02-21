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
    public void accept(IFavoringTraitTypeVisitor visitor) {
      visitor.visitAbilityType(this);
    }

    @Override
    public AbilityType[] getTraitTypes() {
      return net.sf.anathema.character.generic.traits.types.AbilityType.values();
    }

    @Override
    public boolean canFavorType(ITraitType type) {
      return type instanceof AbilityType;
    }
  },
  AttributeType {
    @Override
    public void accept(IFavoringTraitTypeVisitor visitor) {
      visitor.visitAttributeType(this);
    }

    @Override
    public AttributeType[] getTraitTypes() {
      return net.sf.anathema.character.generic.traits.types.AttributeType.values();
    }

    @Override
    public boolean canFavorType(ITraitType type) {
      return type instanceof AttributeType;
    }
  },
  VirtueType {
    @Override
    public void accept(IFavoringTraitTypeVisitor visitor) {
      visitor.visitVirtueType(this);
    }

    @Override
    public VirtueType[] getTraitTypes() {
      return net.sf.anathema.character.generic.traits.types.VirtueType.values();
    }

    @Override
    public boolean canFavorType(ITraitType type) {
      return false;
    }
  },
  YoziType {
    @Override
    public void accept(IFavoringTraitTypeVisitor visitor) {
      visitor.visitYoziType(this);
    }

    @Override
    public YoziType[] getTraitTypes() {
      return net.sf.anathema.character.generic.traits.types.YoziType.values();
    }

    @Override
    public boolean canFavorType(ITraitType type) {
      return type instanceof YoziType;
    }
  };

  @Override
  public String getId() {
    return name();
  }

  public abstract void accept(IFavoringTraitTypeVisitor visitor);

  public abstract ITraitType[] getTraitTypes();

  public abstract boolean canFavorType(ITraitType type);
}