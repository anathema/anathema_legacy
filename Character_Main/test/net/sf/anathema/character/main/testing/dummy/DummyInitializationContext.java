package net.sf.anathema.character.main.testing.dummy;

import net.sf.anathema.character.generic.impl.magic.persistence.ISpellCache;
import net.sf.anathema.character.generic.impl.template.magic.ICharmProvider;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.type.CharacterTypes;
import net.sf.anathema.hero.model.InitializationContext;

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
  public ISpellCache getSpellCache() {
    throw new UnsupportedOperationException();
  }
}
