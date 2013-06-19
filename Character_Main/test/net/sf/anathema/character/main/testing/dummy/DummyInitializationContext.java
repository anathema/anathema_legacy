package net.sf.anathema.character.main.testing.dummy;

import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.generic.impl.template.magic.ICharmProvider;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.type.CharacterTypes;
import net.sf.anathema.character.main.hero.InitializationContext;

import java.util.List;

public class DummyInitializationContext implements InitializationContext {

  public DummyGenericCharacter character;
  public final DummyHero dummyHero;

  public DummyInitializationContext() {
    this(new DummyHero());
    this.character = new DummyGenericCharacter(dummyHero.getTemplate());
  }

  public DummyInitializationContext(DummyHero hero) {
    this.dummyHero = hero;
  }

  @Override
  public IMagicCollection getMagicCollection() {
    return character;
  }

  @Override
  public CharacterTypes getCharacterTypes() {
    throw new UnsupportedOperationException();
  }

  @Override
  public ITemplateRegistry getTemplateRegistry() {
    throw new UnsupportedOperationException();
  }

  @Override
  public ICharmProvider getCharmProvider() {
    throw new UnsupportedOperationException();
  }

  @Override
  public ICharacterListening getCharacterListening() {
    return dummyHero.listening;
  }

  @Override
  public <T> List<T> getAllRegistered(Class<T> interfaceClass) {
    return character.getAllRegistered(interfaceClass);
  }
}
