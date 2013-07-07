package net.sf.anathema.character.main.framework;

import net.sf.anathema.character.main.caste.ICasteCollection;
import net.sf.anathema.character.main.data.IExtensibleDataSet;
import net.sf.anathema.character.main.template.magic.ICharmProvider;
import net.sf.anathema.character.main.template.ITemplateRegistry;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.character.main.type.ICharacterType;
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