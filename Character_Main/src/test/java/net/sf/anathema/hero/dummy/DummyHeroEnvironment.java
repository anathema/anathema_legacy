package net.sf.anathema.hero.dummy;

import net.sf.anathema.character.framework.type.CharacterTypes;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.framework.data.ExtensibleDataSet;
import net.sf.anathema.hero.template.ITemplateRegistry;
import net.sf.anathema.initialization.repository.DataFileProvider;
import org.mockito.Mockito;

public class DummyHeroEnvironment implements HeroEnvironment {

  public DummyCharacterTypes characterTypes = new DummyCharacterTypes();
  public DataFileProvider mockFileProvider = Mockito.mock(DataFileProvider.class);
  public ObjectFactory mockObjectFactory = Mockito.mock(ObjectFactory.class);

  @Override
  public CharacterTypes getCharacterTypes() {
    return characterTypes;
  }

  @Override
  public ObjectFactory getObjectFactory() {
    return mockObjectFactory;
  }

  @Override
  public ITemplateRegistry getTemplateRegistry() {
    throw new UnsupportedOperationException();
  }

  @Override
  public DataFileProvider getDataFileProvider() {
    return mockFileProvider;
  }

  @Override
  public <T extends ExtensibleDataSet> T getDataSet(Class<T> set) {
    throw new UnsupportedOperationException();
  }
}