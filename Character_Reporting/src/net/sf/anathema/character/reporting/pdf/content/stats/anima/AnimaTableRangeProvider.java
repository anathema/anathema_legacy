package net.sf.anathema.character.reporting.pdf.content.stats.anima;

import net.sf.anathema.character.generic.character.IGenericCharacter;

public class AnimaTableRangeProvider implements IAnimaTableRangeProvider {

  private static final String[] ranges = new String[]{"1-3", "4-7", "8-10", "11-15", "16+"};
  //

  @Override
  public String getRange(int level, IGenericCharacter character) {
    return ranges[level];
  }
}
