package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.magic.general.ICost;

public interface ICostStringBuilder<T extends ICost> {

  public String getCostString(T cost);
}