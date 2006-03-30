package net.sf.anathema.character.generic;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.type.CharacterType;

public interface IBasicCharacterData {
  
  public ICasteType getCasteType();

  public CharacterType getCharacterType();
  
  public ITemplateType getTemplateType();

  public boolean isExperienced();

  public IExaltedRuleSet getRuleSet();
}