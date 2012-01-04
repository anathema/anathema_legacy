package net.sf.anathema.character.abyssal.reporting.rendering;

import net.sf.anathema.character.reporting.pdf.content.stats.anima.AnimaTableStealthProvider;
import net.sf.anathema.lib.resources.IResources;

public class Anima1stEditionStealthProvider extends net.sf.anathema.character.reporting.pdf.content.stats.anima.AnimaTableStealthProvider {

  public Anima1stEditionStealthProvider(IResources resources) {
    super(resources);
  }

  @Override
  protected String getLevelTwoStealth() {
    return getNormalStealth();
  }
}
