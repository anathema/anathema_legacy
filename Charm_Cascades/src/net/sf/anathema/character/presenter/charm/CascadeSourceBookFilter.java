package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import org.dom4j.Element;

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

  @Override
  protected boolean mustBeShownDueToCircumstance(ICharm charm) {
    return false;
  }

  @Override
  public void save(Element parent) {
    //nothing to do, applies to characters only
  }

  @Override
  public boolean load(Element node) {
    //applies to characters only
    return false;
  }
}