package net.sf.anathema.hero.framework;

import net.sf.anathema.character.main.framework.ICharacterTemplateExtensionResourceCache;
import net.sf.anathema.character.main.framework.ICharacterTemplateRegistryCollection;
import net.sf.anathema.character.main.framework.data.IExtensibleDataSet;
import net.sf.anathema.character.main.framework.data.IExtensibleDataSetProvider;
import net.sf.anathema.character.main.magic.parser.charms.ICharmCache;
import net.sf.anathema.character.main.template.ITemplateRegistry;
import net.sf.anathema.character.main.template.TemplateRegistry;
import net.sf.anathema.character.main.template.magic.CharmProvider;
import net.sf.anathema.character.main.template.magic.ICharmProvider;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.character.main.type.ReflectionCharacterTypes;
import net.sf.anathema.character.main.xml.registry.CharacterTemplateRegistryCollection;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.initialization.repository.DataFileProvider;

public class HeroEnvironmentImpl implements HeroEnvironment {

  private final ITemplateRegistry templateRegistry = new TemplateRegistry();
  private final ICharacterTemplateRegistryCollection templateRegistries;
  private final ICharmProvider charmProvider;
  private final DataFileProvider dataFileProvider;
  private final IExtensibleDataSetProvider dataSetProvider;
  private final ObjectFactory objectFactory;
  private final CharacterTypes characterTypes;

  public HeroEnvironmentImpl(DataFileProvider dataFileProvider, ObjectFactory objectFactory, IExtensibleDataSetProvider dataSetProvider) {
    this.objectFactory = objectFactory;
    this.dataFileProvider = dataFileProvider;
    this.dataSetProvider = dataSetProvider;
    this.charmProvider = new CharmProvider(getDataSet(ICharmCache.class));
    this.templateRegistries = new CharacterTemplateRegistryCollection(dataSetProvider.getDataSet(ICharacterTemplateExtensionResourceCache.class));
    this.characterTypes = new ReflectionCharacterTypes(objectFactory);
  }

  @Override
  public ITemplateRegistry getTemplateRegistry() {
    return templateRegistry;
  }

  @Override
  public ICharacterTemplateRegistryCollection getCharacterTemplateRegistries() {
    return templateRegistries;
  }

  @Override
  public ObjectFactory getObjectFactory() {
    return objectFactory;
  }

  @Override
  public CharacterTypes getCharacterTypes() {
    return characterTypes;
  }

  @Override
  public ICharmProvider getCharmProvider() {
    return charmProvider;
  }

  @Override
  public DataFileProvider getDataFileProvider() {
    return dataFileProvider;
  }

  @Override
  public <T extends IExtensibleDataSet> T getDataSet(Class<T> set) {
    return dataSetProvider.getDataSet(set);
  }
}
