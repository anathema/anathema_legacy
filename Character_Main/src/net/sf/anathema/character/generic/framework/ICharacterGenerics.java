package net.sf.anathema.character.generic.framework;

import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.data.IExtensibleDataSet;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.character.generic.impl.template.magic.ICharmProvider;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.character.generic.type.CharacterTypes;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.initialization.repository.DataFileProvider;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IRegistry;

public interface ICharacterGenerics {

  ITemplateRegistry getTemplateRegistry();

  IRegistry<String, IAdditionalModelFactory> getAdditionalModelFactoryRegistry();

  IRegistry<String, IAdditionalPersisterFactory> getAdditonalPersisterFactoryRegistry();

  IIdentificateRegistry<IGlobalAdditionalTemplate> getGlobalAdditionalTemplateRegistry();

  ICharacterTemplateRegistryCollection getCharacterTemplateRegistries();

  IRegistry<ICharacterType, ICasteCollection> getCasteCollectionRegistry();

  IRegistry<String, IAdditionalTemplateParser> getAdditionalTemplateParserRegistry();

  ICharmProvider getCharmProvider();

  DataFileProvider getDataFileProvider();

  <T extends IExtensibleDataSet> T getDataSet(Class<T> set);

  ObjectFactory getInstantiater();

  CharacterTypes getCharacterTypes();
}