package net.sf.anathema.hero.model;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.impl.magic.persistence.ISpellCache;
import net.sf.anathema.character.generic.impl.template.magic.ICharmProvider;
import net.sf.anathema.character.generic.type.CharacterTypes;
import net.sf.anathema.character.main.charm.options.CharmTemplateRetriever;
import net.sf.anathema.character.main.charm.options.DefaultCharmTemplateRetriever;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.initialization.repository.DataFileProvider;

public class ModelInitializationContext implements InitializationContext {

  private ICharacterGenerics generics;

  public ModelInitializationContext(ICharacterGenerics generics) {
    this.generics = generics;
  }


  @Override
  @Deprecated
  public ISpellCache getSpellCache() {
    return generics.getDataSet(ISpellCache.class);
  }

  @Override
  public ObjectFactory getObjectFactory() {
    return generics.getInstantiater();
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
