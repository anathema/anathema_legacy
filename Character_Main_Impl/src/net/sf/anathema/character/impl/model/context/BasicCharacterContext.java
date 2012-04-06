package net.sf.anathema.character.impl.model.context;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.type.ICharacterType;

public class BasicCharacterContext implements IBasicCharacterData {

  private final IGenericCharacter character;

  public BasicCharacterContext(IGenericCharacter character) {
    this.character = character;
  }

  @Override
  public ICasteType getCasteType() {
    return character.getCasteType();
  }

  @Override
  public ICharacterType getCharacterType() {
    return getTemplateType().getCharacterType();
  }

  @Override
  public boolean isExperienced() {
    return character.isExperienced();
  }

  @Override
  public ITemplateType getTemplateType() {
    return character.getTemplate().getTemplateType();
  }
}