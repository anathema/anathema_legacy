package net.sf.anathema.character.sidereal.reporting;

import net.sf.anathema.character.reporting.common.stats.anima.AnimaTableStealthProvider;
import net.sf.anathema.lib.resources.IResources;

public class SiderealAnimaTableStealthProvider extends AnimaTableStealthProvider {

  public SiderealAnimaTableStealthProvider(IResources resources) {
    super(resources);
  }

  @Override
  protected String getLevelTwoStealth() {
    return getNormalStealth();
  }

  @Override
  protected String getLevelThreeStealth() {
    return getString("Sheet.AnimaTable.Stealth2InDark"); //$NON-NLS-1$
  }
}
