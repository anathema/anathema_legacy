package net.sf.anathema.character.reporting.pdf.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;

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

  @Override
  public boolean hasContent() {
    return true;
  }
}
