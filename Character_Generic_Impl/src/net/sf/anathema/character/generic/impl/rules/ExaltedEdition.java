package net.sf.anathema.character.generic.impl.rules;

import net.sf.anathema.character.generic.rules.IEditionVisitor;
import net.sf.anathema.character.generic.rules.IExaltedEdition;

public enum ExaltedEdition implements IExaltedEdition {
  FirstEdition {
    @Override
    public void accept(IEditionVisitor visitor) {
      visitor.visitFirstEdition(this);
    }
  },
  SecondEdition {
    @Override
    public void accept(IEditionVisitor visitor) {
      visitor.visitSecondEdition(this);
    }
  };

  @Override
  public String getId() {
    return name();
  }
}