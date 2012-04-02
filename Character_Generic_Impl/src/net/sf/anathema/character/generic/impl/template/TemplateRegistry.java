package net.sf.anathema.character.generic.impl.template;

import net.sf.anathema.character.generic.template.ICharacterExternalsTemplate;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.ICharacterType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TemplateRegistry implements ITemplateRegistry {

  private final HashMap<ITemplateType, ICharacterTemplate> templatesByType = new HashMap<ITemplateType, ICharacterTemplate>();

  @Override
  public ICharacterExternalsTemplate[] getAllSupportedTemplates(ICharacterType type) {
    List<ICharacterTemplate> typeTemplates = new ArrayList<ICharacterTemplate>();
    for (ITemplateType templateType : templatesByType.keySet()) {
      if (templateType.getCharacterType().equals(type)) {
        ICharacterTemplate template = getTemplate(templateType);
        if (template != null) {
          typeTemplates.add(template);
        }
      }
    }
    return typeTemplates.toArray(new ICharacterTemplate[typeTemplates.size()]);
  }

  @Override
  public ICharacterTemplate getDefaultTemplate(ICharacterType type) {
    ITemplateType templateType = new TemplateType(type);
    return getTemplate(templateType);
  }

  @Override
  public ICharacterTemplate getTemplate(ICharacterExternalsTemplate template) {
    return templatesByType.get(template.getTemplateType());
  }

  @Override
  public ICharacterTemplate getTemplate(ITemplateType type) {
    return templatesByType.get(type);
  }

  @Override
  public void register(ICharacterTemplate template) {
    templatesByType.put(template.getTemplateType(), template);
  }
}