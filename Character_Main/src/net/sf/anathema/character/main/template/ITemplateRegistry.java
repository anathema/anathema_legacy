package net.sf.anathema.character.main.template;

import net.sf.anathema.character.main.type.ICharacterType;

public interface ITemplateRegistry {

  HeroTemplate[] getAllSupportedTemplates(ICharacterType type);

  void register(HeroTemplate template);

  HeroTemplate getDefaultTemplate(ICharacterType type);

  HeroTemplate getTemplate(ITemplateType type);
}