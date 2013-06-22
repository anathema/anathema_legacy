package net.sf.anathema.character.impl.model.context;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;

import java.util.List;

public class CharacterModelContext implements ICharacterModelContext {

  private final IGenericCharacter character;

  public CharacterModelContext(IGenericCharacter character) {
    this.character = character;
  }

  @Override
  public <T> List<T> getAllRegistered(Class<T> interfaceClass) {
    return character.getAllRegistered(interfaceClass);
  }
}