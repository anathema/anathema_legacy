package net.sf.anathema.character.main.magic;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.IGenericCombo;
import net.sf.anathema.character.main.magic.charms.ICombo;

public class GenericCombo implements IGenericCombo {

  private final ICombo combo;

  public GenericCombo(ICombo combo) {
    this.combo = combo;
  }

  @Override
  public String getName() {
    return combo.getName().getText();
  }

  @Override
  public ICharm[] getCharms() {
    return combo.getCharms();
  }
}