package net.sf.anathema.character.reporting.extended.common.anima;

import net.sf.anathema.character.generic.character.IGenericCharacter;

public interface IAnimaTableRangeProvider {

  public String getRange(int level, IGenericCharacter character);
}