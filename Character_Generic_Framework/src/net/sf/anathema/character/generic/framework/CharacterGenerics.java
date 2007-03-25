package net.sf.anathema.character.generic.framework;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.NullAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.backgrounds.BackgroundRegistry;
import net.sf.anathema.character.generic.framework.module.object.CharacterModuleObjectMap;
import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.CharacterTemplateRegistryCollection;
import net.sf.anathema.character.generic.impl.template.TemplateRegistry;
import net.sf.anathema.character.generic.impl.template.magic.CharmProvider;
import net.sf.anathema.character.generic.impl.template.magic.ICharmProvider;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.registry.IdentificateRegistry;
import net.sf.anathema.lib.registry.Registry;

public class CharacterGenerics implements ICharacterGenerics {

  private final IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry = new BackgroundRegistry();
  private final ITemplateRegistry templateRegistry = new TemplateRegistry();
  private final IRegistry<String, IAdditionalModelFactory> additionalModelRegistry = new Registry<String, IAdditionalModelFactory>();
  private final IRegistry<String, IAdditionalViewFactory> additionalViewRegistry = new Registry<String, IAdditionalViewFactory>();
  private final IRegistry<String, IAdditionalPersisterFactory> additionalPersisterRegistry;
  private final IIdentificateRegistry<IGlobalAdditionalTemplate> additionalTemplateRegistry = new IdentificateRegistry<IGlobalAdditionalTemplate>();
  private final ICharacterTemplateRegistryCollection templateRegistries = new CharacterTemplateRegistryCollection();
  private final IRegistry<ICharacterType, ICasteCollection> casteCollectionRegistry = new Registry<ICharacterType, ICasteCollection>();
  private final IRegistry<String, IAdditionalTemplateParser> additionalTemplateParserRegistry = new Registry<String, IAdditionalTemplateParser>();
  private final IRegistry<ICharacterType, IMagicStats[]> genericCharmRegistry = new Registry<ICharacterType, IMagicStats[]>();
  private final ICharmProvider charmProvider = new CharmProvider();
  private final CharacterModuleObjectMap moduleObjectMap = new CharacterModuleObjectMap();
  private final IDataFileProvider dataFileProvider;

  public CharacterGenerics(IDataFileProvider dataFileProvider) {
    this.additionalPersisterRegistry = new Registry<String, IAdditionalPersisterFactory>(
        new NullAdditionalPersisterFactory());
    this.dataFileProvider = dataFileProvider;
  }

  public IIdentificateRegistry<IBackgroundTemplate> getBackgroundRegistry() {
    return backgroundRegistry;
  }

  public ITemplateRegistry getTemplateRegistry() {
    return templateRegistry;
  }

  public IRegistry<String, IAdditionalModelFactory> getAdditionalModelFactoryRegistry() {
    return additionalModelRegistry;
  }

  public IRegistry<String, IAdditionalViewFactory> getAdditionalViewFactoryRegistry() {
    return additionalViewRegistry;
  }

  public IRegistry<String, IAdditionalPersisterFactory> getAdditonalPersisterFactoryRegistry() {
    return additionalPersisterRegistry;
  }

  public IIdentificateRegistry<IGlobalAdditionalTemplate> getGlobalAdditionalTemplateRegistry() {
    return additionalTemplateRegistry;
  }

  public ICharacterTemplateRegistryCollection getCharacterTemplateRegistries() {
    return templateRegistries;
  }

  public IRegistry<ICharacterType, ICasteCollection> getCasteCollectionRegistry() {
    return casteCollectionRegistry;
  }

  public IRegistry<String, IAdditionalTemplateParser> getAdditionalTemplateParserRegistry() {
    return additionalTemplateParserRegistry;
  }

  @Override
  public IRegistry<ICharacterType, IMagicStats[]> getGenericCharmStatsRegistry() {
    return genericCharmRegistry;
  }

  public ICharmProvider getCharmProvider() {
    return charmProvider;
  }

  public CharacterModuleObjectMap getModuleObjectMap() {
    return moduleObjectMap;
  }

  public IDataFileProvider getDataFileProvider() {
    return dataFileProvider;
  }
}