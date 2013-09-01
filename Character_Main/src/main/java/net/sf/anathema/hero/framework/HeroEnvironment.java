package net.sf.anathema.hero.framework;

import net.sf.anathema.character.main.framework.ICharacterTemplateRegistryCollection;
import net.sf.anathema.character.main.framework.data.ExtensibleDataSet;
import net.sf.anathema.character.main.template.ITemplateRegistry;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.initialization.repository.DataFileProvider;

public interface HeroEnvironment {

  ITemplateRegistry getTemplateRegistry();

  ICharacterTemplateRegistryCollection getCharacterTemplateRegistries();

  DataFileProvider getDataFileProvider();

  <T extends ExtensibleDataSet> T getDataSet(Class<T> set);

  ObjectFactory getObjectFactory();

  CharacterTypes getCharacterTypes();
}