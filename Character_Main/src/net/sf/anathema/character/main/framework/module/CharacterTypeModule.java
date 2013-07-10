package net.sf.anathema.character.main.framework.module;

import net.sf.anathema.character.main.framework.HeroEnvironment;
import net.sf.anathema.character.main.framework.ICharacterTemplateRegistryCollection;
import net.sf.anathema.character.main.framework.ICharacterTemplateResourceCache;
import net.sf.anathema.character.main.magic.parser.charms.ICharmCache;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.ICharacterExternalsTemplate;
import net.sf.anathema.character.main.template.ITemplateRegistry;
import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.xml.GenericCharacterTemplate;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.ResourceFile;

import java.util.ArrayList;
import java.util.List;

public abstract class CharacterTypeModule implements ICharacterModule {

  private ICharacterType type;

  protected CharacterTypeModule(ICharacterType type) {
    this.type = type;
  }

  @Override
  public void addCharacterTemplates(HeroEnvironment characterGenerics) {
    ICharacterTemplateResourceCache cache = characterGenerics.getDataSet(ICharacterTemplateResourceCache.class);
    for (ResourceFile templateResource : cache.getTemplateResourcesForType(type.getId())) {
      GenericCharacterTemplate template = registerParsedTemplate(characterGenerics, templateResource);
      if (template != null) {
        template.setCustomTemplate(cache.isCustomTemplate(templateResource));
      }
    }
  }

  @Override
  public void registerCommonData(HeroEnvironment characterGenerics) {
    // Nothing to do
  }

  protected final GenericCharacterTemplate registerParsedTemplate(HeroEnvironment generics, ResourceFile resource) {
    ICharacterTemplateRegistryCollection characterTemplateRegistries = generics.getCharacterTemplateRegistries();
    new net.sf.anathema.character.main.xml.CharacterTemplateParser(generics.getCharacterTypes(), characterTemplateRegistries, generics.getCasteCollectionRegistry(), generics.getDataSet(ICharmCache.class));
    try {
      GenericCharacterTemplate template = characterTemplateRegistries.getCharacterTemplateRegistry().get(resource);
      generics.getTemplateRegistry().register(template);
      return template;
    } catch (PersistenceException e) {
      Logger.getLogger(CharacterTypeModule.class).error(resource.getFileName(), e);
      return null;
    }
  }

  protected ITemplateType[] getDefaultAndCustomTemplates(HeroEnvironment generics) {
    List<ITemplateType> types = new ArrayList<>();
    ITemplateRegistry templateRegistry = generics.getTemplateRegistry();
    HeroTemplate defaultTemplate = templateRegistry.getDefaultTemplate(type);
    types.add(defaultTemplate.getTemplateType());
    ICharacterExternalsTemplate[] templatesForType = templateRegistry.getAllSupportedTemplates(type);
    for (ICharacterExternalsTemplate template : templatesForType) {
      if (templateRegistry.getTemplate(template).isCustomTemplate()) {
        types.add(template.getTemplateType());
      }
    }
    return types.toArray(new ITemplateType[types.size()]);
  }
}