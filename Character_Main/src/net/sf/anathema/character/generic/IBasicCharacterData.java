package net.sf.anathema.character.generic;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.type.ICharacterType;

public interface IBasicCharacterData {

  CasteType getCasteType();

  ICharacterType getCharacterType();

  ITemplateType getTemplateType();

  boolean isExperienced();
}