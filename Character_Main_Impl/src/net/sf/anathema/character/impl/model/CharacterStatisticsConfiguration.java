package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;

import static net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet.SecondEdition;

public class CharacterStatisticsConfiguration implements IAnathemaWizardModelTemplate {

  private ICharacterTemplate template;
  private IExaltedRuleSet ruleSet = SecondEdition;

  public ICharacterTemplate getTemplate() {
    return template;
  }

  public IExaltedRuleSet getRuleSet() {
    return ruleSet;
  }

  public void setTemplate(ICharacterTemplate template) {
    this.template = template;
  }
}