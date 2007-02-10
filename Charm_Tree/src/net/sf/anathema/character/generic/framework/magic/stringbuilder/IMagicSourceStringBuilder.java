package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.magic.IMagic;

public interface IMagicSourceStringBuilder<T extends IMagic> {

  public String createSourceString(T magic);

  public String createShortSourceString(T charm);
}