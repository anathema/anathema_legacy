package net.sf.anathema.character.generic.impl.template;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
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

  private final HashMap<ITemplateType, ICharacterTemplate> table2 = new HashMap<ITemplateType, ICharacterTemplate>();

  @Override
  public ICharacterExternalsTemplate[] getAllSupportedTemplates(ICharacterType type) {
    List<ICharacterTemplate> typeTemplates = new ArrayList<ICharacterTemplate>();
    for (ITemplateType templateType : table2.keySet()) {
      if (templateType.getCharacterType().equals(type)) {
        ICharacterTemplate template = getTemplate(templateType, ExaltedEdition.SecondEdition);
        if (template != null) {
          typeTemplates.add(template);
        }
      }
    }
    return typeTemplates.toArray(new ICharacterTemplate[typeTemplates.size()]);
  }

  @Override
  public ICharacterTemplate getDefaultTemplate(ICharacterType type, IExaltedEdition edition) {
    ITemplateType templateType = new TemplateType(type);
    return getTemplate(templateType, edition);
  }

  @Override
  public ICharacterTemplate getTemplate(ICharacterExternalsTemplate template) {
    return table2.get(template.getTemplateType());
  }

  @Override
  public ICharacterTemplate getTemplate(ITemplateType type, IExaltedEdition edition) {
    return table2.get(type);
  }

  @Override
  public void register(ICharacterTemplate template) {
    table2.put(template.getTemplateType(), template);
  }
}