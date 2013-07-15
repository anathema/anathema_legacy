package net.sf.anathema.character.main.template;

import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.lib.util.Identifier;

public interface ITemplateType {

  CharacterType getCharacterType();

  Identifier getSubType();
}