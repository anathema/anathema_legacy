package net.sf.anathema.character.main.testing.dummy;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.type.ICharacterType;

public class DummyBasicCharacterData implements IBasicCharacterData {

  private CasteType casteType;
  private ICharacterType characterType;

  @Override
  public CasteType getCasteType() {
    return casteType;
  }

  @Override
  public ICharacterType getCharacterType() {
    return characterType;
  }

  @Override
  public boolean isExperienced() {
    return false;
  }

  public void setCasteType(CasteType casteType) {
    this.casteType = casteType;
  }

  public void setCharacterType(ICharacterType characterType) {
    this.characterType = characterType;
  }

  @Override
  public ITemplateType getTemplateType() {
    return null;
  }
}