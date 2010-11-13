package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public interface IRulesConfiguration {

  public ITypedDescription<IExaltedRuleSet> getRuleSet();
}