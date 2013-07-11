package net.sf.anathema.hero.combos.sheet.content;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.hero.combos.display.presenter.ICombo;

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
  public Charm[] getCharms() {
    return combo.getCharms();
  }
}