package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.magic.IMagic;

public interface IMagicSourceStringBuilder {

  public String createSourceString(IMagic magic, boolean includeHeader);
}