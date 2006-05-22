package net.sf.anathema.character.generic.framework;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.module.object.CharacterModuleObjectMap;
import net.sf.anathema.character.generic.framework.reporting.template.ICharacterReportTemplate;
import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.CharacterTemplateRegistryCollection;
import net.sf.anathema.character.generic.impl.template.TemplateRegistry;
import net.sf.anathema.character.generic.impl.template.magic.CharmProvider;
import net.sf.anathema.character.generic.impl.template.magic.ICharmProvider;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.registry.CollectionRegistry;
import net.sf.anathema.lib.registry.ICollectionRegistry;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.registry.IdentificateRegistry;
import net.sf.anathema.lib.registry.Registry;

public class CharacterGenerics implements ICharacterGenerics {

  private final IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry = new IdentificateRegistry<IBackgroundTemplate>();
  private final ITemplateRegistry templateRegistry = new TemplateRegistry();
  private final IRegistry<String, IAdditionalModelFactory> additionalModelRegistry = new Registry<String, IAdditionalModelFactory>();
  private final IRegistry<String, IAdditionalViewFactory> additionalViewRegistry = new Registry<String, IAdditionalViewFactory>();
  private final IRegistry<String, IAdditionalPersisterFactory> additionalPersisterRegistry = new Registry<String, IAdditionalPersisterFactory>();
  private final ICollectionRegistry<IGlobalAdditionalTemplate> additionalTemplateRegistry = new CollectionRegistry<IGlobalAdditionalTemplate>();
  private final ICollectionRegistry<ICharacterReportTemplate> reportTemplateRegistry = new CollectionRegistry<ICharacterReportTemplate>();
  private final ICharacterTemplateRegistryCollection templateRegistries = new CharacterTemplateRegistryCollection();
  private final IRegistry<CharacterType, ICasteCollection> casteCollectionRegistry = new Registry<CharacterType, ICasteCollection>();
  private final IRegistry<String, IAdditionalTemplateParser> additionalTemplateParserRegistry = new Registry<String, IAdditionalTemplateParser>();
  private final ICharmProvider charmProvider = new CharmProvider();
  private final CharacterModuleObjectMap moduleObjectMap = new CharacterModuleObjectMap();

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

  public ICollectionRegistry<ICharacterReportTemplate> getReportTemplateRegistry() {
    return reportTemplateRegistry;
  }

  public ICollectionRegistry<IGlobalAdditionalTemplate> getGlobalAdditionalTemplateRegistry() {
    return additionalTemplateRegistry;
  }

  public ICharacterTemplateRegistryCollection getCharacterTemplateRegistries() {
    return templateRegistries;
  }

  public IRegistry<CharacterType, ICasteCollection> getCasteCollectionRegistry() {
    return casteCollectionRegistry;
  }

  public IRegistry<String, IAdditionalTemplateParser> getAdditionalTemplateParserRegistry() {
    return additionalTemplateParserRegistry;
  }

  public ICharmProvider getCharmProvider() {
    return charmProvider;
  }
  
  public CharacterModuleObjectMap getModuleObjectMap() {
    return moduleObjectMap;
  }
}