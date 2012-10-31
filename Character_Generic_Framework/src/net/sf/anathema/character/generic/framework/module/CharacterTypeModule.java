package net.sf.anathema.character.generic.framework.module;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterTemplateResourceCache;
import net.sf.anathema.character.generic.framework.xml.GenericCharacterTemplate;
import net.sf.anathema.character.generic.template.ICharacterExternalsTemplate;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.resources.ResourceFile;

import java.util.ArrayList;
import java.util.List;

public abstract class CharacterTypeModule extends NullObjectCharacterModuleAdapter {

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    ICharacterTemplateResourceCache cache = characterGenerics.getDataSet(ICharacterTemplateResourceCache.class);
    for (ResourceFile templateResource : cache.getTemplateResourcesForType(getType().getId())) {
      GenericCharacterTemplate template = registerParsedTemplate(characterGenerics, templateResource);
      if (template != null) {
        template.setCustomTemplate(cache.isCustomTemplate(templateResource));
      }
    }
  }

  protected ITemplateType[] getDefaultAndCustomTemplates(ICharacterGenerics generics) {
    List<ITemplateType> types = new ArrayList<>();
    ITemplateRegistry templateRegistry = generics.getTemplateRegistry();
    ICharacterTemplate defaultTemplate = templateRegistry.getDefaultTemplate(getType());
    types.add(defaultTemplate.getTemplateType());
    ICharacterExternalsTemplate[] templatesForType = templateRegistry.getAllSupportedTemplates(getType());
    for (ICharacterExternalsTemplate template : templatesForType) {
      if (templateRegistry.getTemplate(template).isCustomTemplate()) {
        types.add(template.getTemplateType());
      }
    }
    return types.toArray(new ITemplateType[types.size()]);
  }

  protected abstract ICharacterType getType();
}
