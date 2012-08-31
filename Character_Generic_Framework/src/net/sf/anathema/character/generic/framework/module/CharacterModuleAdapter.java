package net.sf.anathema.character.generic.framework.module;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterTemplateResourceProvider;
import net.sf.anathema.character.generic.framework.ICharacterTemplateRegistryCollection;
import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObject;
import net.sf.anathema.character.generic.framework.xml.CharacterTemplateParser;
import net.sf.anathema.character.generic.framework.xml.GenericCharacterTemplate;
import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.resources.ResourceFile;

public abstract class CharacterModuleAdapter<M extends ICharacterModuleObject> implements ICharacterModule<M> {

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) throws InitializationException {
    // Nothing to do
  }

  @Override
  public void addCharacterTemplates(ICharacterTemplateResourceProvider provider, ICharacterGenerics characterGenerics) {
    // Nothing to do
  }

  @Override
  public void addBackgroundTemplates(ICharacterGenerics generics) {
    // Nothing to do
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    // Nothing to do
  }

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    // Nothing to do
  }
  
  protected final void registerParsedTemplate(ICharacterGenerics generics, String id, String prefix) {
	  ICharacterTemplateRegistryCollection characterTemplateRegistries = generics.getCharacterTemplateRegistries();
	  IRegistry<String, IAdditionalTemplateParser> additionalTemplateParserRegistry = generics.getAdditionalTemplateParserRegistry();
	  new CharacterTemplateParser(characterTemplateRegistries,
			  generics.getCasteCollectionRegistry(),
			  generics.getCharmProvider(),
			  generics.getDataSet(ICharmCache.class),
			  generics.getBackgroundRegistry(),
			  additionalTemplateParserRegistry);
	  try {
	      GenericCharacterTemplate template = characterTemplateRegistries.getCharacterTemplateRegistry().get(id, prefix);
	      generics.getTemplateRegistry().register(template);
	  } catch (PersistenceException e) {
	      Logger.getLogger(CharacterModuleAdapter.class).error(id, e);
	  }
  }
  
  protected final GenericCharacterTemplate registerParsedTemplate(ICharacterGenerics generics, ResourceFile resource) {
	  ICharacterTemplateRegistryCollection characterTemplateRegistries = generics.getCharacterTemplateRegistries();
	  IRegistry<String, IAdditionalTemplateParser> additionalTemplateParserRegistry = generics.getAdditionalTemplateParserRegistry();
	  new CharacterTemplateParser(characterTemplateRegistries,
			  generics.getCasteCollectionRegistry(),
			  generics.getCharmProvider(),
			  generics.getDataSet(ICharmCache.class),
			  generics.getBackgroundRegistry(),
			  additionalTemplateParserRegistry);
	  try {
	      GenericCharacterTemplate template = characterTemplateRegistries.getCharacterTemplateRegistry().get(resource);
	      generics.getTemplateRegistry().register(template);
	      return template;
	  } catch (PersistenceException e) {
	      Logger.getLogger(CharacterModuleAdapter.class).error(resource.getFileName(), e);
	      return null;
	  }
  }
}
