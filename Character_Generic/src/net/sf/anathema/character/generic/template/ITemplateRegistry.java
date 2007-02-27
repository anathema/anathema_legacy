package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.type.ICharacterType;

public interface ITemplateRegistry {

  public ICharacterExternalsTemplate[] getAllSupportedTemplates(ICharacterType type);

  public void register(ICharacterTemplate template);

  public ICharacterTemplate getDefaultTemplate(ICharacterType type, IExaltedEdition edition);

  public ICharacterTemplate getTemplate(ITemplateType type, IExaltedEdition edition);

  public ICharacterTemplate getTemplate(ICharacterExternalsTemplate template);
}