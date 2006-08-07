package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.lib.workflow.wizard.selection.IItemCreationTemplate;

public class CharacterStatisticsConfiguration implements IItemCreationTemplate {

  private ICharacterTemplate template;
  private IExaltedRuleSet ruleSet;

  public ICharacterTemplate getTemplate() {
    return template;
  }

  public IExaltedRuleSet getRuleSet() {
    return ruleSet;
  }

  public void setTemplate(ICharacterTemplate template) {
    this.template = template;
  }

  public void setRuleSet(IExaltedRuleSet ruleSet) {
    this.ruleSet = ruleSet;
  }
}