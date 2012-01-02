package net.sf.anathema.character.reporting.pdf.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;

public class BasicContent implements SubContent {

  private IGenericCharacter character;

  public BasicContent(IGenericCharacter character) {
    this.character = character;
  }

  public boolean isFirstEdition() {
    return character.getTemplate().getEdition() == ExaltedEdition.FirstEdition;
  }

  public boolean isSecondEdition() {
    return character.getTemplate().getEdition() == ExaltedEdition.SecondEdition;
  }

  public boolean isExalt() {
    return getCharacterType().isExaltType();
  }

  private ICharacterType getCharacterType() {
    return character.getTemplate().getTemplateType().getCharacterType();
  }

  public boolean isOfType(CharacterType type) {
    return getCharacterType() == type;
  }

  @Override
  public boolean hasContent() {
    return true;
  }
}
