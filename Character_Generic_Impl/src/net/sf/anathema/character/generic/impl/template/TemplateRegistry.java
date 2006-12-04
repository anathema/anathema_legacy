package net.sf.anathema.character.generic.impl.template;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ICharacterExternalsTemplate;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.IUnsupportedTemplate;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.collection.Table;

public class TemplateRegistry implements ITemplateRegistry {

  private final Table<ITemplateType, IExaltedEdition, ICharacterTemplate> table = new Table<ITemplateType, IExaltedEdition, ICharacterTemplate>();

  public ICharacterExternalsTemplate[] getAllSupportedTemplates(CharacterType type) {
    List<ICharacterTemplate> typeTemplates = new ArrayList<ICharacterTemplate>();
    for (ITemplateType templateType : table.getPrimaryKeys()) {
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

  public ICharacterTemplate getDefaultTemplate(CharacterType type, IExaltedEdition edition) {
    ITemplateType templateType = new TemplateType(type);
    return getTemplate(templateType, edition);
  }

  public ICharacterTemplate getTemplate(ICharacterExternalsTemplate template) {
    return table.get(template.getTemplateType(), template.getEdition());
  }

  public ICharacterTemplate getTemplate(ITemplateType type, IExaltedEdition edition) {
    return table.get(type, edition);
  }

  public void register(ICharacterTemplate template) {
    table.add(template.getTemplateType(), template.getEdition(), template);
  }
}