package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import org.dom4j.Element;

import java.util.List;

public class CascadeSourceBookFilter extends SourceBookCharmFilter {
  private IExaltedRuleSet ruleSet;

  public CascadeSourceBookFilter(IExaltedRuleSet ruleSet) {
    super(ruleSet.getEdition());
    this.ruleSet = ruleSet;
    for (ExaltedEdition thisEdition : ExaltedEdition.values()) {
      prepareEdition(thisEdition);
    }
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
  protected List<IExaltedSourceBook> getBooks(IExaltedEdition edition) {
    return new SourceBookCollection().getSourcesForEdition(edition);
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