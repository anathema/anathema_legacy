package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.type.ICharacterType;

public interface ITemplateRegistry {

  ICharacterExternalsTemplate[] getAllSupportedTemplates(ICharacterType type);

  void register(ICharacterTemplate template);

  ICharacterTemplate getDefaultTemplate(ICharacterType type);

  ICharacterTemplate getTemplate(ITemplateType type);

  ICharacterTemplate getTemplate(ICharacterExternalsTemplate template);
}