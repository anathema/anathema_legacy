package net.sf.anathema.character.impl.model.context;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharmContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IGenericSpecialtyContext;
import net.sf.anathema.character.generic.template.magic.IGenericCharmConfiguration;

import java.util.List;

public class CharacterModelContext implements ICharacterModelContext, ICharmContext {

  private final IGenericCharacter character;
  private CharacterListening characterListening;
  private final IBasicCharacterData characterData;

  public CharacterModelContext(IGenericCharacter character, CharacterListening characterListening) {
    this.character = character;
    this.characterListening = characterListening;
    this.characterData = new BasicCharacterContext(character);
  }

  @Override
  public IAdditionalModel getAdditionalModel(String id) {
    return character.getAdditionalModel(id);
  }

  @Override
  public IGenericTraitCollection getTraitCollection() {
    return character.getTraitCollection();
  }

  @Override
  public IMagicCollection getMagicCollection() {
    return character;
  }

  @Override
  public CharacterListening getCharacterListening() {
    return characterListening;
  }

  @Override
  public IBasicCharacterData getBasicCharacterContext() {
    return characterData;
  }

  @Override
  public IGenericCharmConfiguration getCharmConfiguration() {
    return character;
  }

  @Override
  public <T> List<T> getAllRegistered(Class<T> interfaceClass) {
    return character.getAllRegistered(interfaceClass);
  }

  @Override
  public IGenericSpecialtyContext getSpecialtyContext() {
    return new GenericSpecialtyContext(character);
  }
}