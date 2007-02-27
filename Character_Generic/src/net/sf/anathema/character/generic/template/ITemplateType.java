package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.IIdentificate;

public interface ITemplateType {

  public ICharacterType getCharacterType();

  public IIdentificate getSubType();
}