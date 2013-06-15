package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.Identifier;

public interface ITemplateType {

  ICharacterType getCharacterType();

  Identifier getSubType();
}