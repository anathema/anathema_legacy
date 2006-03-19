package net.sf.anathema.acceptance.fixture.character.template;

import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.lib.lang.ArrayUtilities;

public class CheckRulesetsFixture extends AbstractTemplateColumnFixture {

  public String id;

  public boolean isAvailable() {
    ExaltedRuleSet set;
    try {
      set = ExaltedRuleSet.valueOf(id);
    }
    catch (IllegalArgumentException e) {
      return false;
    }
    IExaltedRuleSet[] ruleSets = getTemplate().getRuleSets();
    return ArrayUtilities.contains(ruleSets, set);
  }
}