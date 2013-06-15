package net.sf.anathema.character.generic.traits.types;

import net.sf.anathema.character.generic.traits.ITraitType;

public enum OtherTraitType implements ITraitType {
  Essence() {
    @Override
    public void accept(ITraitTypeVisitor visitor) {
      visitor.visitEssence(this);
    }
  },
  Willpower() {
    @Override
    public void accept(ITraitTypeVisitor visitor) {
      visitor.visitWillpower(this);
    }
  };

  @Override
  public String getId() {
    return name();
  }

  @Override
  public abstract void accept(ITraitTypeVisitor visitor);

  @Override
  public String toString() {
    return getId();
  }
}