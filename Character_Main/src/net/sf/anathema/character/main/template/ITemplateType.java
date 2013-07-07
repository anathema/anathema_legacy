package net.sf.anathema.character.main.template;

import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.lib.util.Identifier;

public interface ITemplateType {

  ICharacterType getCharacterType();

  Identifier getSubType();
}