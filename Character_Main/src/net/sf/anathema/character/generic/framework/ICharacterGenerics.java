package net.sf.anathema.character.generic.framework;

import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.data.IExtensibleDataSet;
import net.sf.anathema.character.generic.impl.template.magic.ICharmProvider;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.type.CharacterTypes;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.initialization.repository.DataFileProvider;
import net.sf.anathema.lib.registry.IRegistry;

public interface ICharacterGenerics {

  ITemplateRegistry getTemplateRegistry();

  ICharacterTemplateRegistryCollection getCharacterTemplateRegistries();

  IRegistry<ICharacterType, ICasteCollection> getCasteCollectionRegistry();

  ICharmProvider getCharmProvider();

  DataFileProvider getDataFileProvider();

  <T extends IExtensibleDataSet> T getDataSet(Class<T> set);

  ObjectFactory getInstantiater();

  CharacterTypes getCharacterTypes();
}