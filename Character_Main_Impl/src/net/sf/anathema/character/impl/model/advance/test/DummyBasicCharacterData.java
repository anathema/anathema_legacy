package net.sf.anathema.character.impl.model.advance.test;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.type.CharacterType;

public class DummyBasicCharacterData implements IBasicCharacterData {

  private boolean isExperienced = false;
  private ICasteType casteType;
  private CharacterType characterType;
  private IExaltedRuleSet ruleSet;

  public ICasteType getCasteType() {
    return casteType;
  }

  public CharacterType getCharacterType() {
    return characterType;
  }

  public boolean isExperienced() {
    return isExperienced;
  }

  public IExaltedRuleSet getRuleSet() {
    return ruleSet;
  }

  public void setCasteType(ICasteType casteType) {
    this.casteType = casteType;
  }

  public void setCharacterType(CharacterType characterType) {
    this.characterType = characterType;
  }

  public void setExperienced(boolean isExperienced) {
    this.isExperienced = isExperienced;
  }

  public void setRuleSet(IExaltedRuleSet ruleSet) {
    this.ruleSet = ruleSet;
  }

  public ITemplateType getTemplateType() {
    return null;
  }
}