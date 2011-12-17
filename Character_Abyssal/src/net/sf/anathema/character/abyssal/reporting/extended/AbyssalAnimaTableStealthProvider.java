package net.sf.anathema.character.abyssal.reporting.extended;

import net.sf.anathema.character.reporting.extended.common.anima.AnimaTableStealthProvider;
import net.sf.anathema.lib.resources.IResources;

public class AbyssalAnimaTableStealthProvider extends AnimaTableStealthProvider {

  public AbyssalAnimaTableStealthProvider(IResources resources) {
    super(resources);
  }

  @Override
  protected String getLevelTwoStealth() {
    return getNormalStealth();
  }
}