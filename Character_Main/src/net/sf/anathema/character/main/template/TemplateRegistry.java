package net.sf.anathema.character.main.template;

import net.sf.anathema.character.main.type.ICharacterType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TemplateRegistry implements ITemplateRegistry {

  private final HashMap<ITemplateType, HeroTemplate> templatesByType = new HashMap<>();

  @Override
  public HeroTemplate[] getAllSupportedTemplates(ICharacterType type) {
    List<HeroTemplate> typeTemplates = new ArrayList<>();
    for (ITemplateType templateType : templatesByType.keySet()) {
      if (templateType.getCharacterType().equals(type)) {
        HeroTemplate template = getTemplate(templateType);
        if (template != null) {
          typeTemplates.add(template);
        }
      }
    }
    return typeTemplates.toArray(new HeroTemplate[typeTemplates.size()]);
  }

  @Override
  public HeroTemplate getDefaultTemplate(ICharacterType type) {
    ITemplateType templateType = new TemplateType(type);
    return getTemplate(templateType);
  }

  @Override
  public HeroTemplate getTemplate(ITemplateType type) {
    return templatesByType.get(type);
  }

  @Override
  public void register(HeroTemplate template) {
    templatesByType.put(template.getTemplateType(), template);
  }
}