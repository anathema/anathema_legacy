package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;

public class CharacterStatisticsConfiguration {

  private ICharacterTemplate template;
  private IExaltedRuleSet ruleSet;
  
  public CharacterStatisticsConfiguration(ICharacterTemplate template, IExaltedRuleSet ruleSet) {
    this.template = template;
    this.ruleSet = ruleSet;
  }

  public ICharacterTemplate getTemplate() {
    return template;
  }
  
  public IExaltedRuleSet getRuleSet() {
    return ruleSet;
  }
}