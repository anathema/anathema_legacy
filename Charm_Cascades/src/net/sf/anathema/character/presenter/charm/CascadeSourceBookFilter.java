package net.sf.anathema.character.presenter.charm;

import com.google.common.collect.Lists;
import net.sf.anathema.cascades.presenter.EditionCharmGroups;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import org.dom4j.Element;

import java.util.List;

public class CascadeSourceBookFilter extends SourceBookCharmFilter {
  private final IExaltedRuleSet ruleSet;
  private final EditionCharmGroups charmGroups;


  public CascadeSourceBookFilter(IExaltedRuleSet ruleSet, EditionCharmGroups charmGroups) {
    super(ruleSet.getEdition());
    this.ruleSet = ruleSet;
    this.charmGroups = charmGroups;
    prepareEdition(ExaltedEdition.SecondEdition);
  }


  @Override
  protected IExaltedEdition getEdition() {
    return ruleSet.getEdition();
  }

  @Override
  protected List<ICharmGroup> getAllCharmGroups(IExaltedEdition edition) {
    return Lists.newArrayList(charmGroups.getCharmGroups(edition));
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