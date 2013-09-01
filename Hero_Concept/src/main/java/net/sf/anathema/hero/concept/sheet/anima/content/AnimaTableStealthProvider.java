package net.sf.anathema.hero.concept.sheet.anima.content;

import net.sf.anathema.framework.environment.Resources;

public class AnimaTableStealthProvider implements IAnimaTableStealthProvider {

  private final Resources resources;

  public AnimaTableStealthProvider(Resources resources) {
    this.resources = resources;
  }

  @Override
  public String getStealth(int level) {
    switch (level) {
      case 0:
        return getLevelOneStealth();
      case 1:
        return getLevelTwoStealth();
      case 2:
        return getLevelThreeStealth();
      default:
        return getImpossibleStealth();
    }
  }

  protected String getLevelThreeStealth() {
    return getImpossibleStealth();
  }

  protected String getLevelTwoStealth() {
    return getDifficultStealth();
  }

  private String getLevelOneStealth() {
    return getNormalStealth();
  }

  private String getDifficultStealth() {
    return "+2";
  }

  private String getImpossibleStealth() {
    return resources.getString("Sheet.AnimaTable.StealthImpossible");
  }

  protected String getNormalStealth() {
    return resources.getString("Sheet.AnimaTable.StealthNormal");
  }

  protected String getString(String key) {
    return resources.getString(key);
  }
}
