package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class CascadeSourceBookFilter extends SourceBookCharmFilter {
  private IExaltedRuleSet ruleSet;

  public CascadeSourceBookFilter(IExaltedRuleSet ruleSet) {
    super(ruleSet.getEdition());
    this.ruleSet = ruleSet;
  }

  @Override
  protected IExaltedEdition getEdition() {
    return ruleSet.getEdition();
  }
}