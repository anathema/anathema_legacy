package net.sf.anathema.hero.model;

import net.sf.anathema.character.main.magic.persistence.ISpellCache;
import net.sf.anathema.character.main.template.magic.ICharmProvider;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.character.main.charm.options.CharmTemplateRetriever;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.initialization.repository.DataFileProvider;

public interface InitializationContext {

  CharacterTypes getCharacterTypes();

  ICharmProvider getCharmProvider();

  ISpellCache getSpellCache();

  ObjectFactory getObjectFactory();

  DataFileProvider getDataFileProvider();

  CharmTemplateRetriever getCharmTemplateRetriever();
}
