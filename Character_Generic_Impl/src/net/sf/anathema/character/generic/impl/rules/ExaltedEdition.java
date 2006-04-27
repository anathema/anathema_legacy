package net.sf.anathema.character.generic.impl.rules;

import net.sf.anathema.character.generic.rules.IEditionVisitor;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public enum ExaltedEdition implements IExaltedEdition {
  FirstEdition {
    public void accept(IEditionVisitor visitor) {
      visitor.visitFirstEdition(this);
    }
  },
  SecondEdition {
    public void accept(IEditionVisitor visitor) {
      visitor.visitSecondEdition(this);
    }
  };

  public String getId() {
    return name();
  }

  public static IExaltedRuleSet getDefaultRuleSet(IExaltedEdition edition) {
    final IExaltedRuleSet[] rules = new IExaltedRuleSet[1];
    edition.accept(new IEditionVisitor() {
      public void visitFirstEdition(IExaltedEdition visitedEdition) {
        rules[0] = ExaltedRuleSet.CoreRules;
      }

      public void visitSecondEdition(IExaltedEdition visitedEdition) {
        rules[0] = ExaltedRuleSet.SecondEdition;
      }
    });
    return rules[0];
  }
}