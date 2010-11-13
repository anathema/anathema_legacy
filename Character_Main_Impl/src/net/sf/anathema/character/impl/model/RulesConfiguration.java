package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.model.IRulesConfiguration;
import net.sf.anathema.character.model.ITypedDescription;

public class RulesConfiguration implements IRulesConfiguration {
  private final ITypedDescription<IExaltedRuleSet> ruleSet;

  public RulesConfiguration(IExaltedRuleSet rules) {
    this.ruleSet = new TypedDescription<IExaltedRuleSet>(rules);
  }

  public ITypedDescription<IExaltedRuleSet> getRuleSet() {
    return ruleSet;
  }
}