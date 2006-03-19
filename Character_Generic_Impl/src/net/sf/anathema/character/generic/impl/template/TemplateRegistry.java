package net.sf.anathema.character.generic.impl.template;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.IUnsupportedTemplate;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.collection.ListOrderedSet;
import net.sf.anathema.lib.registry.Registry;

public class TemplateRegistry extends Registry<TemplateType, ICharacterTemplate> implements ITemplateRegistry {

  public CharacterType[] getCharacterTypes() {
    Set<CharacterType> characterTypes = new ListOrderedSet<CharacterType>();
    for (TemplateType type : getIds(new TemplateType[0])) {
      characterTypes.add(type.getCharacterType());
    }
    List<CharacterType> sortedTypes = new ArrayList<CharacterType>();
    for (CharacterType type : CharacterType.values()) {
      if (characterTypes.contains(type)) {
        sortedTypes.add(type);
      }
    }
    return sortedTypes.toArray(new CharacterType[sortedTypes.size()]);
  }

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