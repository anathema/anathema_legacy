package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.type.CharacterType;

public interface ITemplateRegistry {

  public ICharacterTemplate getDefaultTemplate(CharacterType characterType);

  public ICharacterTemplate getTemplate(TemplateType templateType);

  public ICharacterTemplate[] getAllSupportedTemplates(CharacterType type);

  public void register(ICharacterTemplate template);
}