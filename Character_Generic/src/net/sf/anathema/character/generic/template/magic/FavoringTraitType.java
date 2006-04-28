package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.lib.util.IIdentificate;

public enum FavoringTraitType implements IIdentificate {
  AbilityType() {
    @Override
    public void accept(IFavoringTraitTypeVisitor visitor) {
      visitor.visitAbilityType(this);
    }
  },
  AttributeType() {
    @Override
    public void accept(IFavoringTraitTypeVisitor visitor) {
      visitor.visitAttributeType(this);
    }
  };

  public String getId() {
    return name();
  }

  public abstract void accept(IFavoringTraitTypeVisitor visitor);
}