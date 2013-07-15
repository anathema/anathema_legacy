package net.sf.anathema.character.main.traits.types;

import net.sf.anathema.character.main.traits.TraitType;

public enum OtherTraitType implements TraitType {
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