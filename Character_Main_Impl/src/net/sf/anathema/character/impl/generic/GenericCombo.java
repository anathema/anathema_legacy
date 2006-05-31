package net.sf.anathema.character.impl.generic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IGenericCombo;
import net.sf.anathema.character.model.charm.ICombo;

public class GenericCombo implements IGenericCombo {
  
  private final ICombo combo;

  public GenericCombo(ICombo combo) {
    this.combo = combo;
  }

  public String getName() {
    return combo.getName().getText();
  }

  public ICharm[] getCharms() {
    return combo.getCharms();
  }
}