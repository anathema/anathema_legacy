package net.sf.anathema.demo.character.reporting;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IGenericCombo;

public class DemoGenericCombo implements IGenericCombo {
  
  private final String name;
  private final ICharm[] charms;

  public DemoGenericCombo(String name, ICharm[] charms) {
    this.name = name;
    this.charms = charms;
  }

  @Override
  public ICharm[] getCharms() {
    return charms;
  }

  @Override
  public String getName() {
    return name;
  }
}