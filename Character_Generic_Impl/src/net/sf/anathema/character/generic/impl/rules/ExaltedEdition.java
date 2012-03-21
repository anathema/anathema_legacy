package net.sf.anathema.character.generic.impl.rules;

import net.sf.anathema.character.generic.rules.IEditionVisitor;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public enum ExaltedEdition implements IExaltedEdition {
  FirstEdition {
    @Override
    public void accept(IEditionVisitor visitor) {
      visitor.visitFirstEdition(this);
    }

    @Override
    public IExaltedRuleSet getDefaultRuleset() {
      return null;
    }
  },
  SecondEdition {
    @Override
    public void accept(IEditionVisitor visitor) {
      visitor.visitSecondEdition(this);
    }

    @Override
    public IExaltedRuleSet getDefaultRuleset() {
      return ExaltedRuleSet.SecondEdition;
    }
  };

  @Override
  public String getId() {
    return name();
  }
}