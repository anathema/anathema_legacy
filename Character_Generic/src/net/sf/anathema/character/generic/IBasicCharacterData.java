package net.sf.anathema.character.generic;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.type.ICharacterType;

public interface IBasicCharacterData {

  ICasteType getCasteType();

  ICharacterType getCharacterType();

  ITemplateType getTemplateType();

  boolean isExperienced();
}