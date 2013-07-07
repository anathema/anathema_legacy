package net.sf.anathema.hero.model;

import net.sf.anathema.character.main.framework.HeroEnvironment;
import net.sf.anathema.character.main.magic.parser.ISpellCache;
import net.sf.anathema.character.main.template.magic.ICharmProvider;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.character.main.magic.model.charms.options.CharmTemplateRetriever;
import net.sf.anathema.character.main.magic.model.charms.options.DefaultCharmTemplateRetriever;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.initialization.repository.DataFileProvider;

public class ModelInitializationContext implements InitializationContext {

  private HeroEnvironment generics;

  public ModelInitializationContext(HeroEnvironment generics) {
    this.generics = generics;
  }


  @Override
  @Deprecated
  public ISpellCache getSpellCache() {
    return generics.getDataSet(ISpellCache.class);
  }

  @Override
  public ObjectFactory getObjectFactory() {
    return generics.getObjectFactory();
  }

  @Override
  public DataFileProvider getDataFileProvider() {
    return generics.getDataFileProvider();
  }

  @Override
  public CharmTemplateRetriever getCharmTemplateRetriever() {
    return new DefaultCharmTemplateRetriever(generics.getTemplateRegistry());
  }

  @Override
  public CharacterTypes getCharacterTypes() {
    return generics.getCharacterTypes();
  }

  @Override
  public ICharmProvider getCharmProvider() {
    return generics.getCharmProvider();
  }
}
