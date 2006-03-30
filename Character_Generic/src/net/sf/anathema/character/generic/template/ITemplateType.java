package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.util.IIdentificate;

public interface ITemplateType {

  public CharacterType getCharacterType();

  public IIdentificate getSubType();

}