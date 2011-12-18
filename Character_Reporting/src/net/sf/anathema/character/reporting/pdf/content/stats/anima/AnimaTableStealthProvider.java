package net.sf.anathema.character.reporting.pdf.content.stats.anima;

import net.sf.anathema.lib.resources.IResources;

public class AnimaTableStealthProvider implements IAnimaTableStealthProvider {

  private final IResources resources;

  public AnimaTableStealthProvider(IResources resources) {
    this.resources = resources;
  }

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
    return "+2"; //$NON-NLS-1$
  }

  private String getImpossibleStealth() {
    return resources.getString("Sheet.AnimaTable.StealthImpossible"); //$NON-NLS-1$
  }

  protected String getNormalStealth() {
    return resources.getString("Sheet.AnimaTable.StealthNormal"); //$NON-NLS-1$
  }

  protected String getString(String key) {
    return resources.getString(key);
  }
}
