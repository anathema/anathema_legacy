package net.sf.anathema.character.generic.impl.template;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.IUnsupportedTemplate;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.registry.Registry;

public class TemplateRegistry extends Registry<TemplateType, ICharacterTemplate> implements ITemplateRegistry {

  public ICharacterTemplate getDefaultTemplate(CharacterType type) {
    TemplateType templateType = new TemplateType(type);
    return get(templateType);
  }

  public ICharacterTemplate[] getAllSupportedTemplates(CharacterType type) {
    List<ICharacterTemplate> typeTemplates = new ArrayList<ICharacterTemplate>();
    for (TemplateType templateType : getKeys()) {
      if (templateType.getCharacterType().equals(type)) {
        ICharacterTemplate template = getTemplate(templateType);
        if (!(template instanceof IUnsupportedTemplate)) {
          typeTemplates.add(template);
        }
      }
    }
    return typeTemplates.toArray(new ICharacterTemplate[typeTemplates.size()]);
  }

  public ICharacterTemplate getTemplate(TemplateType templateType) {
    return get(templateType);
  }

  public void register(ICharacterTemplate template) {
    register(template.getTemplateType(), template);
  }
}