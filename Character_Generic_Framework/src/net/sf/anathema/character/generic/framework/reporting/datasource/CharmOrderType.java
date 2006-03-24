package net.sf.anathema.character.generic.framework.reporting.datasource;

import net.sf.anathema.lib.util.IIdentificate;

public enum CharmOrderType implements IIdentificate {
  TreeOrder() {
    public void accept(ICharmOrderTypeVisitor visitor) {
      visitor.visitTreeOrder(this);
    }
  },
  Alphabet() {
    public void accept(ICharmOrderTypeVisitor visitor) {
      visitor.visitAlphabet(this);
    }
  };

  public String getId() {
    return name();
  }

  public abstract void accept(ICharmOrderTypeVisitor visitor);
}