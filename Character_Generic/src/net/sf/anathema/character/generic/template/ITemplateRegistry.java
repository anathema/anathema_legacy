package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.type.ICharacterType;

public interface ITemplateRegistry {

  ICharacterExternalsTemplate[] getAllSupportedTemplates(ICharacterType type);

  void register(ICharacterTemplate template);

  ICharacterTemplate getDefaultTemplate(ICharacterType type, IExaltedEdition edition);

  ICharacterTemplate getTemplate(ITemplateType type, IExaltedEdition edition);

  ICharacterTemplate getTemplate(ICharacterExternalsTemplate template);
}