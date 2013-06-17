package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.type.ICharacterType;

public interface ITemplateRegistry {

  ICharacterExternalsTemplate[] getAllSupportedTemplates(ICharacterType type);

  void register(HeroTemplate template);

  HeroTemplate getDefaultTemplate(ICharacterType type);

  HeroTemplate getTemplate(ITemplateType type);

  HeroTemplate getTemplate(ICharacterExternalsTemplate template);
}