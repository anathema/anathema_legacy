package net.sf.anathema.character.generic.impl.rules;

import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IRuleSetVisitor;

public enum ExaltedRuleSet implements IExaltedRuleSet {

  CoreRules {
    public void accept(IRuleSetVisitor visitor) {
      visitor.visitCoreRules(this);
    }
  },
  PowerCombat {
    public void accept(IRuleSetVisitor visitor) {
      visitor.visitPowerCombat(this);
    }
  },
  SecondEdition {
    public void accept(IRuleSetVisitor visitor) {
      visitor.visitSecondEdition(this);
    }
  };

  public String getId() {
    return name();
  }

  public static IExaltedRuleSet[] getFirstEditionSets() {
    return new IExaltedRuleSet[] { CoreRules, PowerCombat };
  }

  public static IExaltedRuleSet[] getSecondEditionSets() {
    return new IExaltedRuleSet[] { SecondEdition };
  }

  @Override
  public String toString() {
    return getId();
  }
}