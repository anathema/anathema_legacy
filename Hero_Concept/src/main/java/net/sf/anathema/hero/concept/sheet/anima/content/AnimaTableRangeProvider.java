package net.sf.anathema.hero.concept.sheet.anima.content;

import net.sf.anathema.hero.model.Hero;

public class AnimaTableRangeProvider implements IAnimaTableRangeProvider {

  private static final String[] ranges = new String[]{"1-3", "4-7", "8-10", "11-15", "16+"};
  //

  @Override
  public String getRange(int level, Hero hero) {
    return ranges[level];
  }
}
