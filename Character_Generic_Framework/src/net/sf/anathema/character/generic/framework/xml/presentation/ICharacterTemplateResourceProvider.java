package net.sf.anathema.character.generic.framework.xml.presentation;

import net.sf.anathema.character.generic.type.CharacterType;

public interface ICharacterTemplateResourceProvider {

  public String getBallResource(CharacterType characterType);

  public String getUnselectedBallResource();
}