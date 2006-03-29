package net.sf.anathema.character.generic.impl.template;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.IUnsupportedTemplate;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.CharacterType;

public class TemplateRegistry implements ITemplateRegistry {

  private final Table<TemplateType, IExaltedEdition, ICharacterTemplate> table = new Table<TemplateType, IExaltedEdition, ICharacterTemplate>();

  public ICharacterTemplate getDefaultTemplate(CharacterType type, IExaltedEdition edition) {
    TemplateType templateType = new TemplateType(type);
    return getTemplate(templateType, edition);
  }

  public ICharacterTemplate[] getAllSupportedTemplates(CharacterType type) {
    List<ICharacterTemplate> typeTemplates = new ArrayList<ICharacterTemplate>();
    for (TemplateType templateType : table.getPrimaryKeys()) {
      if (templateType.getCharacterType().equals(type)) {
        for (IExaltedEdition edition : ExaltedEdition.values()) {
          ICharacterTemplate template = getTemplate(templateType, edition);
          if (template != null && !(template instanceof IUnsupportedTemplate)) {
            typeTemplates.add(template);
          }
        }
      }
    }
    return typeTemplates.toArray(new ICharacterTemplate[typeTemplates.size()]);
  }

  public void register(ICharacterTemplate template) {
    table.add(template.getTemplateType(), template.getEdition(), template);
  }

  public ICharacterTemplate getTemplate(TemplateType type, IExaltedEdition edition) {
    return table.get(type, edition);
  }
}