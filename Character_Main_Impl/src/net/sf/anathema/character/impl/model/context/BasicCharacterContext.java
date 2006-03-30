package net.sf.anathema.character.impl.model.context;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.type.CharacterType;

public class BasicCharacterContext implements IBasicCharacterData {

  private final IGenericCharacter character;

  public BasicCharacterContext(IGenericCharacter character) {
    this.character = character;
  }

  public ICasteType getCasteType() {
    return character.getCasteType();
  }

  public CharacterType getCharacterType() {
    return getTemplateType().getCharacterType();
  }

  public boolean isExperienced() {
    return character.isExperienced();
  }
  
  public IExaltedRuleSet getRuleSet() {
    return character.getRules();
  }

  public ITemplateType getTemplateType() {
    return character.getTemplate().getTemplateType();
  }
}