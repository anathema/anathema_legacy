package net.sf.anathema.character.main.magic.model.charm.type;

import net.sf.anathema.character.main.magic.model.charm.ICharmTypeVisitor;
import net.sf.anathema.lib.util.Identifier;

public enum CharmType implements Identifier {
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
  Permanent() {
    @Override
    public void accept(ICharmTypeVisitor visitor) {
      visitor.visitPermanent(this);
    }
  },
  Special() {
    @Override
    public void accept(ICharmTypeVisitor visitor) {
      visitor.visitSpecial(this);
    }
  };

  @Override
  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return getId();
  }

  public abstract void accept(ICharmTypeVisitor visitor);
}