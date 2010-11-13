package net.sf.anathema.character.generic.template.abilities;

import net.sf.anathema.lib.util.IIdentificate;

public enum AbilityGroupType implements IIdentificate {

  Life {
    @Override
    public void accept(IAbilityGroupTypeVisitor visitor) {
      visitor.visitLife(this);
    }
  },
  Wisdom {
    @Override
    public void accept(IAbilityGroupTypeVisitor visitor) {
      visitor.visitWisdom(this);
    }
  },
  War {
    @Override
    public void accept(IAbilityGroupTypeVisitor visitor) {
      visitor.visitWar(this);
    }
  };

  public abstract void accept(IAbilityGroupTypeVisitor visitor);

  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return getId();
  }
}