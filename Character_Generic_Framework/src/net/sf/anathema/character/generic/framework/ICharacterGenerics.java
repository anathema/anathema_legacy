package net.sf.anathema.character.generic.framework;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObjectMap;
import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.character.generic.impl.template.magic.ICharmProvider;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IRegistry;

public interface ICharacterGenerics {

  public IIdentificateRegistry<IBackgroundTemplate> getBackgroundRegistry();

  public ITemplateRegistry getTemplateRegistry();

  public IRegistry<String, IAdditionalModelFactory> getAdditionalModelFactoryRegistry();

  public IRegistry<String, IAdditionalViewFactory> getAdditionalViewFactoryRegistry();

  public IRegistry<String, IAdditionalPersisterFactory> getAdditonalPersisterFactoryRegistry();

  public IIdentificateRegistry<IGlobalAdditionalTemplate> getGlobalAdditionalTemplateRegistry();

  public ICharacterTemplateRegistryCollection getCharacterTemplateRegistries();

  public IRegistry<CharacterType, ICasteCollection> getCasteCollectionRegistry();

  public IRegistry<String, IAdditionalTemplateParser> getAdditionalTemplateParserRegistry();

  public ICharmProvider getCharmProvider();

  public ICharacterModuleObjectMap getModuleObjectMap();

  public IDataFileProvider getDataFileProvider();
}