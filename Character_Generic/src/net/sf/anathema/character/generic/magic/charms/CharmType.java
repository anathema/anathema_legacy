package net.sf.anathema.character.generic.magic.charms;

import net.sf.anathema.lib.util.IIdentificate;

public enum CharmType implements IIdentificate {
  Simple() {
    @Override
    public void accept(ICharmTypeVisitor visitor) {
      visitor.visitSimple(this);
    }
  },
  ExtraAction() {
    @Override
    public void accept(ICharmTypeVisitor visitor) {
      visitor.visitExtraAction(this);
    }
  },
  Reflexive() {
    @Override
    public void accept(ICharmTypeVisitor visitor) {
      visitor.visitReflexive(this);
    }
  },
  Supplemental() {
    @Override
    public void accept(ICharmTypeVisitor visitor) {
      visitor.visitSupplemental(this);
    }
  },
  Special() {
    @Override
    public void accept(ICharmTypeVisitor visitor) {
      visitor.visitSpecial(this);
    }
  };

  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return getId();
  }

  public abstract void accept(ICharmTypeVisitor visitor);
}