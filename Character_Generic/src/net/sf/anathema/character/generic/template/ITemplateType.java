package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.Identified;

public interface ITemplateType {

  ICharacterType getCharacterType();

  Identified getSubType();
}