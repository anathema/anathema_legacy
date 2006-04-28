package net.sf.anathema.character.generic.impl.rules;

import net.sf.anathema.character.generic.rules.IEditionVisitor;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public enum ExaltedEdition implements IExaltedEdition {
  FirstEdition {
    public void accept(IEditionVisitor visitor) {
      visitor.visitFirstEdition(this);
    }

    public IExaltedRuleSet getDefaultRuleset() {
      return ExaltedRuleSet.CoreRules;
    }
  },
  SecondEdition {
    public void accept(IEditionVisitor visitor) {
      visitor.visitSecondEdition(this);
    }

    public IExaltedRuleSet getDefaultRuleset() {
      return ExaltedRuleSet.SecondEdition;
    }
  };

  public String getId() {
    return name();
  }
}