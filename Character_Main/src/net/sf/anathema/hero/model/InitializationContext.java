package net.sf.anathema.hero.model;

import net.sf.anathema.character.main.magic.cache.CharmProvider;
import net.sf.anathema.character.main.magic.model.charms.options.CharmTemplateRetriever;
import net.sf.anathema.character.main.magic.parser.spells.ISpellCache;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.initialization.repository.DataFileProvider;

public interface InitializationContext {

  CharacterTypes getCharacterTypes();

  CharmProvider getCharmProvider();

  ISpellCache getSpellCache();

  ObjectFactory getObjectFactory();

  DataFileProvider getDataFileProvider();

  CharmTemplateRetriever getCharmTemplateRetriever();
}
