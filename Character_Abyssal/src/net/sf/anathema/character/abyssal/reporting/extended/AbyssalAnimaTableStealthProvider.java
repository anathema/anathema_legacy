package net.sf.anathema.character.abyssal.reporting.extended;

import net.sf.anathema.character.reporting.pdf.content.stats.anima.*;
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
